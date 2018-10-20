package api;

import api.input.ClickMode;
import api.utilities.Random;
import api.utilities.screen.geometry.ScreenRectangle;
import api.wrappers.RSWidget;
import client.BotInstance;

import java.awt.event.MouseEvent;

/**
 * BonBom
 * Date: 5.1.2018
 * Time: 12.41
 */

public abstract class AbstractScript extends MethodContext {
    public boolean pauseScript = false;
    public boolean pauseWelcomeScreenHandler = false;

    public AbstractScript() {
        super();
        startScript();
    }

    public AbstractScript(BotInstance instance) {
        super(instance);
        startScript();
    }

    public abstract void onStart();

    public abstract int onLoop();

    public abstract void onExit();

    public void startScript() {
        onStart();
        welcomeScreenHandler();

        while(true) {
            try {
                if(!pauseScript) {
                    int value = onLoop();
                    if (value > 0) {
                        sleep(value);
                    } else {
                        break;
                    }
                } else {
                    sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
                sleep(1000);
            }
        }
        onExit();
    }

    public void welcomeScreenHandler() {
        try {
            new Thread(() -> {
                while (true) {
                    try {
                        if(getClient().getGameState() < 30) {
                            pauseWelcomeScreenHandler = false;
                        }
                        if (getClient().getGameState() == 30) {
                            RSWidget widget = getWidgets().get(378, 6);
                            if (widget != null) {
                                if (widget.isVisible()) {
                                    pauseScript = true;
                                    sleep(Random.nextInt(1000, 3000));
                                    System.out.println("Handling welcome screen");
                                    ScreenRectangle rectangle = new ScreenRectangle(widget.getX(), widget.getY(), widget.getWidth(), widget.getHeight());
                                    getMouse().move(rectangle.getGaussianPoint());
                                    getMouse().click(ClickMode.LEFT_BUTTON);
                                    if(sleepUntil(() -> !widget.isVisible(), Random.nextInt(4000, 8000))) {
                                        pauseWelcomeScreenHandler = true;
                                    }
                                    pauseScript = false;
                                }
                            }
                        }
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean handleLogin(String username, String password) {
        ScreenRectangle cancel = new ScreenRectangle(394, 303, 531 - 394, 332 - 303);
        ScreenRectangle existingUser = new ScreenRectangle(396, 276, 525 - 396, 305 - 276);
        ScreenRectangle tryAgain = new ScreenRectangle(312, 258, 446 - 312, 289 - 258);

        if (!getClient().isLoggedIn()) {
            if (getClient().getGameState() ==  10) {
                if (getClient().getLoginState() == 2) {
                    getMouse().move(cancel.getGaussianPoint());
                    getMouse().click(MouseEvent.BUTTON1);
                }
                if (getClient().getLoginState() == 0) {
                    getMouse().move(existingUser.getGaussianPoint());
                    getMouse().click(MouseEvent.BUTTON1);
                    getKeyboard().writeText(username, true);
                    getKeyboard().writeText(password, true);
                    sleepUntil(() -> getClient().getGameState() == 30, Random.nextInt(3000, 6000));
                }
                if (getClient().getLoginState() == 3) {
                    System.out.println("Wrong password");
                    getMouse().move(tryAgain.getGaussianPoint());
                    getMouse().click(MouseEvent.BUTTON1);
                }
            }
        } else {
            return true;
        }
        return false;
    }
}

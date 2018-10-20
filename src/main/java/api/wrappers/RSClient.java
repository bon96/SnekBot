package api.wrappers;

import api.methods.Menu;
import api.wrappers.RSRegion;
import api.wrappers.RSWidget;
import api.wrappers.RSPlayer;
import client.BotInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * BonBom
 * Date: 4.1.2018
 * Time: 13.01
 */

public class RSClient extends RSWrapper {


    public RSClient(BotInstance instance, Object object) {
        super(instance, object);
    }

    public List<RSWidget> getWidgets() {
        List<RSWidget> list = new ArrayList<RSWidget>();
        Object[] widgets = (Object[]) getWidgetsReference();
        for (Object object : widgets) {
            if (object != null) {
                for (Object object2 : (Object[]) object) {
                    if (object2 != null) {
                        list.add(new RSWidget(instance, object2));
                    }
                }
            }
        }
        return list;
    }

    Menu menu;
    public Menu getMenu() {
        if (menu == null) {
            menu = new Menu(this);
        }
        return menu;
    }

    public RSPlayer getLocalPlayer() {
        return new RSPlayer(instance, getLocalPlayerReference());
    }

    public boolean isLoggedIn() {
        return getGameState() == 30 && getCameraX() > 0;
    }


    public int getBaseX() {
        return (int) instance.getHookLoader().getFieldValue("Client.baseX", object);
    }

    public int getBaseY() {
        return (int) instance.getHookLoader().getFieldValue("Client.baseY", object);
    }

    public int getCameraPitch() {
        return (int) instance.getHookLoader().getFieldValue("Client.cameraPitch", object);
    }

    public int getCameraX() {
        return (int) instance.getHookLoader().getFieldValue("Client.cameraX", object);
    }

    public int getCameraY() {
        return (int) instance.getHookLoader().getFieldValue("Client.cameraY", object);
    }

    public int getCameraZ() {
        return (int) instance.getHookLoader().getFieldValue("Client.cameraZ", object);
    }

    public int getCameraYaw() {
        return (int) instance.getHookLoader().getFieldValue("Client.cameraYaw", object);
    }

    public int getClanMembersSize() {
        return (int) instance.getHookLoader().getFieldValue("Client.clanMembersSize", object);
    }

    public String getClanName() {
        return (String) instance.getHookLoader().getFieldValue("Client.clanName", object);
    }

    public String getClanOwner() {
        return (String) instance.getHookLoader().getFieldValue("Client.clanOwner", object);
    }

    public int getcrosshairColorReference() {
        return (int) instance.getHookLoader().getFieldValue("Client.crosshairColor", object);
    }

    public int getCurrentWorld() {
        return (int) instance.getHookLoader().getFieldValue("Client.currentworld", object);
    }

    public int getDestinationX() {
        return (int) instance.getHookLoader().getFieldValue("Client.destinationX", object);
    }

    public int getDestinationY() {
        return (int) instance.getHookLoader().getFieldValue("Client.destinationY", object);
    }

    public int getFriendsSize() {
        return (int) instance.getHookLoader().getFieldValue("Client.friendsSize", object);
    }

    public int getGameCycle() {
        return (int) instance.getHookLoader().getFieldValue("Client.gameCycle", object);
    }

    public int getGameState() {
        return (int) instance.getHookLoader().getFieldValue("Client.gameState", object);
    }

    public String getHost() {
        return (String) instance.getHookLoader().getFieldValue("Client.host", object);
    }

    public int getIdleTime() {
        return (int) instance.getHookLoader().getFieldValue("Client.idleTime", object);
    }

    public boolean isSpellSelected() {
        return (boolean) instance.getHookLoader().getFieldValue("Client.isSpellSelected", object);
    }

    public boolean isWorldSelectorOpen() {
        return (boolean) instance.getHookLoader().getFieldValue("Client.isWorldSelectorOpen", object);
    }

    public int getLastButtonClick() {
        return (int) instance.getHookLoader().getFieldValue("Client.lastButtonClick", object);
    }

    public int getLastButtonClickModA() {
        return (int) instance.getHookLoader().getFieldValue("Client.lastButtonClickModA", object);
    }

    public int getLastButtonClickModM() {
        return (int) instance.getHookLoader().getFieldValue("Client.lastButtonClickModM", object);
    }

    public int getLastClickX() {
        return (int) instance.getHookLoader().getFieldValue("Client.lastClickX", object);
    }

    public int getLastClickY() {
        return (int) instance.getHookLoader().getFieldValue("Client.lastClickY", object);
    }

    public int getLoginState() {
        return (int) instance.getHookLoader().getFieldValue("Client.loginState", object);
    }

    public int getLowestAvailableCameraPitch() {
        return (int) instance.getHookLoader().getFieldValue("Client.lowestAvailableCameraPitch", object);
    }

    public int getMapAngle() {
        return (int) instance.getHookLoader().getFieldValue("Client.mapAngle", object);
    }

    public int getMenuCount() {
        return (int) instance.getHookLoader().getFieldValue("Client.menuCount", object);
    }

    public int getMenuHeight() {
        return (int) instance.getHookLoader().getFieldValue("Client.menuHeight", object);
    }

    public int getMenuWidth() {
        return (int) instance.getHookLoader().getFieldValue("Client.menuWidth", object);
    }

    public int getMenuX() {
        return (int) instance.getHookLoader().getFieldValue("Client.menuX", object);
    }

    public int getMenuY() {
        return (int) instance.getHookLoader().getFieldValue("Client.menuY", object);
    }

    public String getMessage0() {
        return (String) instance.getHookLoader().getFieldValue("Client.message0", object);
    }

    public String getMessage1() {
        return (String) instance.getHookLoader().getFieldValue("Client.message1", object);
    }

    public String getMessage2() {
        return (String) instance.getHookLoader().getFieldValue("Client.message2", object);
    }

    public String getPassword() {
        return (String) instance.getHookLoader().getFieldValue("Client.password", object);
    }

    public int getPlane() {
        return (int) instance.getHookLoader().getFieldValue("Client.plane", object);
    }

    public int getPlayerIndex() {
        return (int) instance.getHookLoader().getFieldValue("Client.playerIndex", object);
    }

    public int getSelectedItemID() {
        return (int) instance.getHookLoader().getFieldValue("Client.selectedItemID", object);
    }

    public int getSelectedItemIndex() {
        return (int) instance.getHookLoader().getFieldValue("Client.selectedItemIndex", object);
    }

    public String getSelectedItemName() {
        return (String) instance.getHookLoader().getFieldValue("Client.selectedItemName", object);
    }

    public String getSelectedSpellName() {
        return (String) instance.getHookLoader().getFieldValue("Client.selectedSpellName", object);
    }

    public int getSelectionState() {
        return (int) instance.getHookLoader().getFieldValue("Client.selectionState", object);
    }

    public String getUsername() {
        return (String) instance.getHookLoader().getFieldValue("Client.username", object);
    }

    public int getZoomExact() {
        return (int) instance.getHookLoader().getFieldValue("Client.zoomExact", object);
    }

    public boolean isMenuOpen() {
        return (boolean) instance.getHookLoader().getFieldValue("Client.menuVisible", object);
    }


    /*
    * Reference methods should be used trough a second getter that
    * processes the information to a more sensible format.
     */

    public Object getCanvasReference() {
        return instance.getHookLoader().getFieldValue("Client.canvas", object);
    }


    public Object getClanMembersReference() {
        return instance.getHookLoader().getFieldValue("Client.clanMembers", object);
    }

    public Object getClickModifierReference() {
        return instance.getHookLoader().getFieldValue("Client.clickModifier", object);
    }

    public Object getCollisionMapsReference() {
        return instance.getHookLoader().getFieldValue("Client.collisionMaps", object);
    }

    public Object getExperienceReference() {
        return instance.getHookLoader().getFieldValue("Client.experience", object);
    }

    public Object getFriendsReference() {
        return instance.getHookLoader().getFieldValue("Client.friends", object);
    }

    public Object getGameSettingsReference() {
        return instance.getHookLoader().getFieldValue("Client.gameSettings", object);
    }

    public Object getGrandExchangeItemsReference() {
        return instance.getHookLoader().getFieldValue("Client.grandExchangeItems", object);
    }

    public Object getGroundItemListReference() {
        return instance.getHookLoader().getFieldValue("Client.groundItemList", object);
    }

    public Object getGroundItemModelCacheReference() {
        return instance.getHookLoader().getFieldValue("Client.groundItemModelCache", object);
    }


    public Object getItemTableReference() {
        return instance.getHookLoader().getFieldValue("Client.itemTable", object);
    }

    public Object getKeyboardReference() {
        return instance.getHookLoader().getFieldValue("Client.keyboard", object);
    }

    public Object getLastActionReference() { //could be long
        return instance.getHookLoader().getFieldValue("Client.lastAction", object);
    }

    public Object getLastActionDifferenceReference() { //could be long
        return instance.getHookLoader().getFieldValue("Client.lastActionDifference", object);
    }

    public Object getLastActionDifferenceModReference() { //could be long
        return instance.getHookLoader().getFieldValue("Client.lastActionDifferenceMod", object);
    }

    public Object getLastActionTimeReference() { //could be long
        return instance.getHookLoader().getFieldValue("Client.lastActionTime", object);
    }

    public Object getLastActionTimeModReference() { //could be long
        return instance.getHookLoader().getFieldValue("Client.lastActionTimeMod", object);
    }

    public Object getLevelReference() {
        return instance.getHookLoader().getFieldValue("Client.level", object);
    }

    public Object getLocalNPCsReference() {
        return instance.getHookLoader().getFieldValue("Client.localNPCs", object);
    }

    public Object getLocalPlayerReference() {
        return instance.getHookLoader().getFieldValue("Client.localPlayer", object);
    }

    public String[] getMenuActionsReference() {
        return (String[]) instance.getHookLoader().getFieldValue("Client.menuActions", object);
    }

    public String[] getMenuOptionsReference() {
        return (String[]) instance.getHookLoader().getFieldValue("Client.menuOptions", object);
    }

    public int[] getMenuOpcodes() {
        return (int[])instance.getHookLoader().getFieldValue("Client.menuOpcodes", object);
    }

    public boolean[] getMenuShiftClickReference() {
        return (boolean[]) instance.getHookLoader().getFieldValue("Client.menuShiftClick", object);
    }

    public int[] getMenuVariables() {
        return (int[]) instance.getHookLoader().getFieldValue("Client.menuVariable", object);
    }

    public int[] getMenuXInteractions() {
        return (int[]) instance.getHookLoader().getFieldValue("Client.menuXInteractions", object);
    }

    public int[] getMenuYInteractions() {
        return (int[]) instance.getHookLoader().getFieldValue("Client.menuYInteractions", object);
    }

    public Object getMessageContainerReference() {
        return instance.getHookLoader().getFieldValue("Client.messageContainer", object);
    }

    public Object getMouseReference() {
        return instance.getHookLoader().getFieldValue("Client.mouse", object);
    }

    public Object getNpcCompositeCacheReference() {
        return instance.getHookLoader().getFieldValue("Client.npcCompositeCache", object);
    }

    public Object getNpcModelCacheReference() {
        return instance.getHookLoader().getFieldValue("Client.npcModelCache", object);
    }

    public Object getObjectCompositeCacheReference() {
        return instance.getHookLoader().getFieldValue("Client.objectCompositeCache", object);
    }

    public Object getObjectModelCacheReference() {
        return instance.getHookLoader().getFieldValue("Client.objectModelCache", object);
    }

    public Object getPlayerModelCacheReference() {
        return instance.getHookLoader().getFieldValue("Client.playerModelCache", object);
    }

    public Object getPlayersReference() {
        return instance.getHookLoader().getFieldValue("Client.players", object);
    }

    public Object getRealLevelReference() {
        return instance.getHookLoader().getFieldValue("Client.realLevel", object);
    }

    public RSRegion getRegionReference() {
        return new RSRegion(instance, instance.getHookLoader().getFieldValue("Client.region", object));
    }

    public Object getSettingsReference() {
        return instance.getHookLoader().getFieldValue("Client.settings", object);
    }

    public Object getSettingsObjectReference() {
        return instance.getHookLoader().getFieldValue("Client.settingsObject", object);
    }

    public Object getSocketWrapperReference() {
        return instance.getHookLoader().getFieldValue("Client.socketWrapper", object);
    }

    public Object getTileHeightsReference() {
        return instance.getHookLoader().getFieldValue("Client.tileHeights", object);
    }

    public Object getTileSettingsReference() {
        return instance.getHookLoader().getFieldValue("Client.tileSettings", object);
    }

    public Object getWidgetBoundsXReference() {
        return instance.getHookLoader().getFieldValue("Client.widgetBoundsX", object);
    }

    public Object getWidgetBoundsYReference() {
        return instance.getHookLoader().getFieldValue("Client.widgetBoundsY", object);
    }

    public Object getWidgetHeightsReference() {
        return instance.getHookLoader().getFieldValue("Client.widgetHeights", object);
    }

    public Object getWidgetModelCacheReference() {
        return instance.getHookLoader().getFieldValue("Client.widgetModelCache", object);
    }

    public Object getWidgetNodesReference() {
        return instance.getHookLoader().getFieldValue("Client.widgetNodes", object);
    }

    public Object getWidgetWidthsReference() {
        return instance.getHookLoader().getFieldValue("Client.widgetWidths", object);
    }

    public Object getWidgetsReference() {
        return instance.getHookLoader().getFieldValue("Client.widgets", object);
    }

    public Object getWorldsReference() {
        return instance.getHookLoader().getFieldValue("Client.worlds", object);
    }
}

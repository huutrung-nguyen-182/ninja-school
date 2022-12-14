package com.hoiuc.server;

import com.hoiuc.assembly.Player;
import com.hoiuc.io.IMessageHandler;
import com.hoiuc.io.Message;
import com.hoiuc.io.Util;

public class Controller implements IMessageHandler {

    @Override
    public void processMessage(Session var1, Message var2) {
        Controller.onMessage(var1, var2);
    }

    @Override
    public void onConnectionFail(Session var1) {}

    @Override
    public void onDisconnected(Session var1) {
        if(var1 != null) {
            var1.outdelay = 5;
        }
    }

    @Override
    public void onConnectOK(Session var1) {}

    public static void onMessage(Session session, Message message) {
        if(session == null) {
            return;
        }
        byte num1 = message.getCommand();
        Player player = session.player;
        switch (num1) {
            case -30: {
                Controller.messageSubCommand(message, player);
                break;
            }
            case -29: {
                Controller.messageNotLogin(message, session);
                break;
            }
            case -28: {
                Controller.messageNotMap(message, player);
                break;
            }
            case -27: {
                session.hansakeMessage();
                break;
            }

            //Public chat
            case -23: {
                HandleController.publicChat(player, message);
                break;
            }
            //Private chat
            case -22: {
                HandleController.privateChat(player, message);
                break;
            }
            //World chat
            case -21: {
                HandleController.worldChat(player, message);
                break;
            }
            //Party chat
            case -20: {
                HandleController.partyChat(player, message);
                break;
            }
            //Clan chat
            case -19: {
                HandleController.clanChat(player, message);
                break;
            }
            //Next map
            case -17: {
                HandleController.nextMap(player, message);
                break;
            }
            //Pick Item
            case -14: {
                HandleController.pickItem(player, message);
                break;
            }
            //Leave Item To Character
            case -12: {
                HandleController.leaveItemToCharacter(player, message);
                break;
            }
            //Wake Up Die Return
            case -10: {
                HandleController.wakeUpDieReturn(player);
                break;
            }
            //Die Return
            case -9: {
                HandleController.dieReturn(player);
                break;
            }
            //Move
            case 1: {
                HandleController.move(player, message);
                break;
            }
            //Fight All
            case 4:
            case 73: {
                HandleController.fightAll(player, message);
                break;
            }
            //Use Item
            case 11: {
                HandleController.useItem(player, message);
                break;
            }
            //Use Item Change Map
            case 12: {
                HandleController.useItemChangeMap(player, message);
                break;
            }
            //Buy Item
            case 13: {
                HandleController.buyItem(player, message);
                break;
            }
            //Sell Item
            case 14: {
                HandleController.sellItem(player, message);
                break;
            }
            //Item Body To Bag
            case 15: {
                HandleController.itemBodyToBag(player, message);
                break;
            }
            //Item Box To Bag
            case 16: {
                HandleController.itemBoxToBag(player, message);
                break;
            }
            //Item Bag To Box
            case 17: {
                HandleController.itemBagToBox(player, message);
                break;
            }
            //Luy???n ???? xu
            case 19: {
                HandleController.stoneSmelting(player, message, true);
                break;
            }
            //Luy???n ???? y??n
            case 20: {
                HandleController.stoneSmelting(player, message, false);
                break;
            }
            //N??ng c???p trang b???
            case 21: {
                HandleController.upgradeEquipment(player, message);
                break;
            }
            //T??ch trang b???
            case 22: {
                HandleController.splitEquipment(player, message);
                break;
            }
            //Xin v??o nh??m
            case 23: {
                HandleController.pleaseParty(player, message);
                break;
            }
            //?????ng ?? xin v??o nh??m
            case 24: {
                HandleController.acceptPleaseParty(player, message);
                break;
            }
            //Request Players
            case 25: {
                break;
            }
            //Ch???n khu
            case 28: {
                HandleController.selectZone(player, message);
                break;
            }
            //Ch???n Menu NPC
            case 29: {
                HandleController.selectMenuNpc(player, message);
                break;
            }
            //M??? khu v???c
            case 36: {
                HandleController.openZone(player);
                break;
            }
            //M??? menu NPC
            case 40: {
                HandleController.openMenuNpc(player, message);
                break;
            }
            //D??ng K??? N??ng
            case 41:
            case 74: {
                HandleController.useSkill(player, message);
                break;
            }
            //L???y th??ng tin Item
            case 42: {
                HandleController.requestItemInfo(player, message);
                break;
            }
            //M???i giao d???ch
            case 43: {
                HandleController.inviteTrade(player, message);
                break;
            }
            //?????ng ?? giao d???ch
            case 44: {
                HandleController.accpetTrade(player, message);
                break;
            }
            //Kho?? giao d???ch
            case 45: {
                HandleController.lockTrade(player, message);
                break;
            }
            //Ho??n t???t giao d???ch
            case 46: {
                HandleController.submitTrade(player);
                break;
            }
            //Ch???n menu npc
            case 47: {
                HandleController.selectMenuNpcTileMap(player, message);
                break;
            }
            //Hu??? giao d???ch
            case 56: {
                HandleController.closeTrade(player);
                break;
            }
            //D???ng t???i
            case 57: {
                HandleController.closeLoad(player);
                break;
            }
            //Th??m b???n b??
            case 59: {
                HandleController.addFriend(player, message);
                break;
            }
            //T???n c??ng qu??i
            case 60: {
                HandleController.attackMob(player, message);
                break;
            }
            //T???n c??ng ng?????i
            case 61: {
                HandleController.attackNinja(player, message);
                break;
            }
            //M???i t??? th??
            case 65: {
                HandleController.inviteSolo(player, message);
                break;
            }
            //?????ng ?? t??? th??
            case 66: {
                HandleController.accpetSolo(player, message);
                break;
            }
            //C???u s??t
            case 68: {
                HandleController.startKillNinja(player, message);
                break;
            }
            //M???i v??o nh??m
            case 79: {
                HandleController.inviteToParty(player, message);
                break;
            }
            //?????ng ?? l???i m???i v??o nh??m
            case 80: {
                HandleController.accpetInviteToParty(player, message);
                break;
            }
            //R???i nh??m
            case 83: {
                HandleController.outParty(player);
                break;
            }
            //X??? l?? nh???p d??? li???u
            case 92: {
                HandleController.inputValue(player, message);
                break;
            }
            //Xem th??ng tin ng?????i ch??i
            case 93: {
                HandleController.viewInfoNinja(player, message);
                break;
            }
            //Xem trang b??? ng?????i ch??i
            case 94: {
                HandleController.viewItemNinja(player, message);
                break;
            }
            //?????ng ?? l??i ????i
            case 99: {
                HandleController.accpetDun(player, message);
                break;
            }
            //V??o xem l??i ????i
            case 100: {
                HandleController.viewDun(player, message);
                break;
            }

            //B??n ?????u gi??
            case 102: {
                HandleController.sendItemToAuction(player, message);
                break;
            }
            case 103: {
                break;
            }
            case 104: {
                HandleController.viewItemAuction(player, message);
                break;
            }
            //mua ????? ?????u gi??
            case 105: {
                HandleController.buyItemAuction(player, message);
                break;
            }
            //G???i giao di???n Yes/No
            case 107: {
                HandleController.yesNoDlg(player, message);
                break;
            }
            //Chuy???n ????? t??? Th??? c?????i v??o h??nh trang
            case 108: {
                HandleController.itemMountToBag(player, message);
                break;
            }
            //Luy???n th???ch
            case 110: {
                HandleController.luyenThach(player, message);
                break;
            }
            //Chuy???n ????? t??? Th??? c?????i v??o h??nh trang
            case 111: {
                HandleController.tinhLuyen(player, message);
                break;
            }
            //D???ch chuy???n trang b???
            case 112: {
                HandleController.dichChuyen(player, message);
                break;
            }
            //Luy???n ng???c
            case 121: {
                HandleController.thienDiaBang(player, message);
                break;
            }
            //Luy???n ng???c
            case 124: {
                HandleController.luyenNgoc(player, message);
                break;
            }
            //G???i Effect
            case 125: {
                HandleController.sendEffect(player, message);
                break;
            }
            default: {
                break;
            }
        }
        if(message != null) {
            message.cleanup();
        }
    }

    private static void messageSubCommand(Message message, Player player) {
        try {
            if(message.reader().available() < 0) {
                return;
            }
            byte b = message.reader().readByte();
            switch (b) {
                case -127: {
                    break;
                }
                case -120: {
                    break;
                }
                //C???ng ??i???m ti???m n??ng 1
                case -109: {
                    HandleController.plusPpoint(player, message);
                    break;
                }
                //C???ng ??i???m k??? n??ng
                case -108: {
                    HandleController.plusSpoint(player, message);
                    break;
                }
                //S???p x???p h??nh trang
                case -107: {
                    HandleController.sortBag(player);
                    break;
                }
                //S???p x???p r????ng
                case -106: {
                    HandleController.sortBox(player);
                    break;
                }
                //G???i xu
                case -105: {
                    HandleController.xuToBox(player, message);
                    break;
                }
                //R??t xu
                case -104: {
                    HandleController.xuToBag(player, message);
                    break;
                }
                //Xem th??ng tin item
                case -103: {
                    HandleController.sendItemInfo(player, message);
                    break;
                }
                //Xem th??ng tin item
                case -93: {
                    HandleController.changeTypePk(player, message);
                    break;
                }
                //T???o nh??m
                case -88: {
                    HandleController.createPatry(player);
                    break;
                }
                //Nh?????ng ch???c nh??m tr?????ng
                case -87: {
                    HandleController.sendKeyParty(player, message);
                    break;
                }
                //M???i ra kh???i nh??m
                case -86: {
                    HandleController.kickParty(player, message);
                    break;
                }
                //Xem danh s??ch b???n b??
                case -85: {
                    HandleController.viewFriendList(player);
                    break;
                }
                //Xem danh s??ch b???n b??
                case -84: {
                    HandleController.viewEnemiesList(player);
                    break;
                }
                //Xo?? b???n b??/th?? ?????ch
                case -83:
                case -82: {
                    HandleController.deleteFriend(player, message);
                    break;
                }
                //D??ng skil h???i sinh
                case -79: {
                    HandleController.useSkillRevive(player, message);
                    break;
                }
                //Tim nh??m
                case -77: {
                    HandleController.findParty(player);
                }
                //Kho?? nh??m
                case -76: {
                    HandleController.statusParty(player, message);
                    break;
                }
                //G??n k??? n??ng
                case -67: {
                    HandleController.pasteSkill(player, message);
                    break;
                }
                //G???i th??ng tin k??? n??ng
                case -65: {
                    HandleController.sendSkill(player, message);
                    break;
                }
                //M???i v??o gia t???c
                case -63: {
                    HandleController.inviteClan(player, message);
                    break;
                }
                //Ch???p nh???n m???i v??o gia t???c
                case -62: {
                    HandleController.acppetInviteClan(player, message);
                    break;
                }
                //Xin v??o gia t???c
                case -61: {
                    HandleController.pleaseClan(player, message);
                    break;
                }
                //?????ng ?? xin v??o gia t???c
                case -60: {
                    HandleController.acppetPleaseClan(player, message);
                    break;
                }
                default: {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(message != null) {
                message.cleanup();
            }
        }
    }

    private static void messageNotLogin(Message message, Session session) {
        try {
            if(message.reader().available() < 0) {
                return;
            }
            byte b = message.reader().readByte();
            Util.Debug("CMD -29 --------------> " + b);
            switch (b) {
                case -127 : {
                    if(session.player == null) {
                        session.loginGame(message);
                    }
                    break;
                }
                case -125 : {
                    session.setConnect(message);
                    break;
                }
                //T??ch s??? l?????ng v???t ph???m
                case -85 : {
                    if (session.player == null) {
                        break;
                    }
                    HandleController.divedeItem(session.player, message);
                    break;
                }
                default: {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(message != null) {
                message.cleanup();
            }
        }
    }

    private static void messageNotMap(Message message, Player player) {
        try {
            if(message.reader().available() < 0) {
                return;
            }
            byte b = message.reader().readByte();
            switch (b) {
                //Ch???n nh??n v???t
                case -126: {
                    HandleController.selectNinja(player, message);
                    break;
                }
                //T???o m???i nh??n v???t
                case -125: {
                    HandleController.createNinja(player, message);
                    break;
                }
                //G???i main data
                case -122: {
                    HandleController.sendDataMain(player);
                    break;
                }
                //G???i data map
                case -121: {
                    HandleController.sendDataMap(player);
                    break;
                }
                //G???i data skill
                case -120: {
                    HandleController.sendDataSkill(player);
                    break;
                }
                //G???i data item
                case -119: {
                    HandleController.sendDataItem(player);
                    break;
                }
                //G???i h??nh ???nh
                case -115: {
                    HandleController.reciveImage(player, message);
                    break;
                }
                //Ghi th??ng tin LOG clan
                case -114: {
                    HandleController.logClan(player);
                    break;
                }
                //th??ng tin clan
                case -113: {
                    HandleController.infoClan(player);
                    break;
                }
                //th??ng tin th??nh vi??n clan
                case -112: {
                    HandleController.infoClanMember(player);
                    break;
                }
                //th??ng tin kho clan
                case -111: {
                    HandleController.infoClanItem(player);
                    break;
                }
                case -109: {
                    HandleController.sendMapInfo(player, message);
                    break;
                }
                //g???i h??nh ???nh mob
                case -108: {
                    HandleController.reciveImageMOB(player, message);
                    break;
                }
                //Ch???n nh??n v???t null
                case -101: {
                    HandleController.selectNinja(player, null);
                    break;
                }
                //Vi???t th??ng b??o Clan
                case -95: {
                    HandleController.setClanAlert(player, message);
                    break;
                }
                //Thay ?????i ki???u Clan
                case -94: {
                    HandleController.changeClanType(player, message);
                    break;
                }
                //R???i clan
                case -93: {
                    HandleController.moveOutClan(player, message);
                    break;
                }
                case -92: {
                    HandleController.outClan(player);
                    break;
                }
                //T??ng c???p clan
                case -91: {
                    HandleController.upLevelClan(player);
                    break;
                }
                //????ng g??p clan
                case -90: {
                    HandleController.inputCoinClan(player, message);
                    break;
                }
                //Ho??n chuy???n trang b???
                case -88: {
                    HandleController.convertUpgrade(player, message);
                    break;
                }
                //M???i l??nh ?????a gia t???c
                case -87: {
                    HandleController.accpetInviteLDGT(player, message);
                    break;
                }
                //t??ch item
                case -85: {
                    HandleController.divedeItem(player, message);
                    break;
                }
                //Nh???n th?????ng r????ng hang ?????ng
                case -82: {
                    HandleController.rewardedCave(player);
                    break;
                }
                //Nh???n th?????ng ??i???m chi???n tr?????ng
                case -79: {
                    HandleController.rewardedCT(player);
                    break;
                }
                //V??ng Xoay May M???n
                case -72: {
                    HandleController.luckyValue(player, message);
                    break;
                }
                //?????ng ?? gia t???c chi???n
                case -68: {
                    HandleController.acceptClanDun(player, message);
                    break;
                }
                //M??? item clan
                case -62: {
                    HandleController.openItemClanLevel(player);
                    break;
                }
                //g???i item cho th??nh vi??n
                case -61: {
                    HandleController.sendItemClanToMember(player, message);
                    break;
                }
                //D??ng item clan
                case -60: {
                    HandleController.useItemClan(player, message);
                    break;
                }
                default: {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(message != null) {
                message.cleanup();
            }
        }
    }


}

package com.hoiuc.stream.thiendiabang;

import com.hoiuc.assembly.Char;
import com.hoiuc.assembly.Map;
import com.hoiuc.server.Manager;
import com.hoiuc.server.ThienDiaBangManager;
import com.hoiuc.stream.Server;

import java.util.HashMap;

public class ThienDiaBang {
    public int tdbId;
    private static int idbase = 0;
    public ThienDiaBangMap[] map;
    public long time;
    Server server;

    public Char ninjaReal;
    public Char ninjaBot;
    public boolean rest;
    public boolean check1;
    public boolean isFinish;
    public boolean isWin;
    public Object LOCK;
    public static HashMap<Integer, ThienDiaBang> tdbs = new HashMap<Integer, ThienDiaBang>();

    public ThienDiaBang(Char ninjaReal, Char ninjaBot) {
        this.LOCK = new Object();
        this.isFinish = false;
        this.rest = false;
        this.isWin = false;
        this.ninjaReal = ninjaReal;
        this.ninjaBot = ninjaBot;
        this.server = Server.gI();
        this.tdbId = ThienDiaBang.idbase++;
        this.time = System.currentTimeMillis() + 900000L;
        this.map = new ThienDiaBangMap[1];

        this.initMap();
        for (byte i = 0; i < this.map.length; ++i) {
            this.map[i].timeMap = this.time;
            this.map[i].start();
        }
        ThienDiaBang.tdbs.put(this.tdbId, this);
    }

    private void initMap() {
        this.map[0] = new ThienDiaBangMap(111, this, this.ninjaReal, this.ninjaBot );
    }

    public void rest() {
        if (!this.rest) {
            this.rest = true;
            try {
                synchronized (this) {
                    Map ma;
                    if(ninjaReal != null) {
                        ninjaReal.tdbTileMap.leave(ninjaReal.p);
                        ninjaReal.p.restCave();
                        ma = Manager.getMapid(ninjaReal.mapLTD);
                        byte k;
                        for (k = 0; k < ma.area.length; ++k) {
                            if (ma.area[k].numplayers < ma.template.maxplayers) {
                                ma.area[k].EnterMap0(ninjaReal);
                                if(ninjaReal.isDie) {
                                    ninjaReal.p.liveFromDead();
                                }
                                break;
                            }
                        }
                    }
                }

                ThienDiaData data;
                if(ThienDiaBangManager.diaBangList.containsKey(this.ninjaBot.name)) {
                    data = ThienDiaBangManager.diaBangList.get(this.ninjaBot.name);
                    if(data != null) {
                        data.setType(1);
                    }
                } else if(ThienDiaBangManager.thienBangList.containsKey(this.ninjaBot.name)) {
                    data = ThienDiaBangManager.thienBangList.get(this.ninjaBot.name);
                    if(data != null) {
                        data.setType(1);
                    }
                }
                this.map[0].close();
                this.map[0] = null;
                synchronized (ThienDiaBang.tdbs) {
                    if(ThienDiaBang.tdbs.containsKey(this.tdbId)){
                        ThienDiaBang.tdbs.remove(this.tdbId);
                    }
                }
            } catch (Exception e) {
                ThienDiaData data;
                if(ThienDiaBangManager.diaBangList.containsKey(this.ninjaBot.name)) {
                    data = ThienDiaBangManager.diaBangList.get(this.ninjaBot.name);
                    if(data != null) {
                        data.setType(1);
                    }
                } else if(ThienDiaBangManager.thienBangList.containsKey(this.ninjaBot.name)) {
                    data = ThienDiaBangManager.thienBangList.get(this.ninjaBot.name);
                    if(data != null) {
                        data.setType(1);
                    }
                }
                this.map[0].close();
                this.map[0] = null;
                synchronized (ThienDiaBang.tdbs) {
                    if(ThienDiaBang.tdbs.containsKey(this.tdbId)){
                        ThienDiaBang.tdbs.remove(this.tdbId);
                    }
                }
            }
        }
    }

    public void finish() {
        if (!this.isFinish) {
            this.isFinish = true;
            if(this.ninjaBot.isDie || this.ninjaBot.hp == 0) {
                this.isWin = true;
            }
            try {
                if(this.ninjaReal != null); {
                    if(!this.isWin) {
                        this.ninjaReal.countTDB--;
                        this.ninjaReal.countWin = 0;
                        this.ninjaReal.p.sendAddchatYellow("B???n ???? thua cu???c, th??? h???ng c???a b???n ???????c gi??? nguy??n.");
                    } else {
                        if(ThienDiaBangManager.register) {
                            int rank = 0;
                            ThienDiaData data;
                            ThienDiaData dataTemp;
                            if(ThienDiaBangManager.diaBangList.containsKey(this.ninjaReal.name)) {
                                data = ThienDiaBangManager.diaBangList.get(this.ninjaReal.name);
                                dataTemp = ThienDiaBangManager.diaBangList.get(this.ninjaBot.name);
                                if(data.getRank() > dataTemp.getRank()) {
                                    rank = data.getRank();
                                    data.setRank(dataTemp.getRank());
                                    dataTemp.setRank(rank);
                                }
                                rank = data.getRank();

                            } else if(ThienDiaBangManager.thienBangList.containsKey(this.ninjaReal.name)) {
                                data = ThienDiaBangManager.thienBangList.get(this.ninjaReal.name);
                                dataTemp = ThienDiaBangManager.thienBangList.get(this.ninjaBot.name);
                                if(data.getRank() > dataTemp.getRank()) {
                                    rank = data.getRank();
                                    data.setRank(dataTemp.getRank());
                                    dataTemp.setRank(rank);
                                }
                                rank = data.getRank();
                            }
                            this.ninjaReal.p.sendAddchatYellow("B???n ???? gi??nh chi???n th???ng, th??? h???ng m???i c???a b???n l??: " + rank);
                        } else {
                            this.ninjaReal.p.sendAddchatYellow("B???n thi ?????u trong th???i gian T???ng k???t, s??? kh??ng ???????c t??nh rank");
                        }
                        this.ninjaReal.countWin++;
                        if(this.ninjaReal.countWin % 10 == 0) {
                            Manager.serverChat("Thi??n ?????a B???ng: ", "Ng?????i ch??i " + this.ninjaReal.name + " ???? chi???n th???ng " + this.ninjaReal.countWin + " l???n li??n ti???p. Ai ???? h??y c???n h???n l???i ??iiiiiiiiiiiiiiiiiiiiii!");
                        }
                        this.ninjaReal.delayJoinTDB = System.currentTimeMillis() + 30000L;
                    }
                }
                Thread.sleep(1000L);
                this.rest();
            } catch (Exception e) {

            }
        }
    }
}

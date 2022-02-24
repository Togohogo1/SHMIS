package com.company.monkeytown;

import java.util.ArrayList;
import java.util.Arrays;

public class Monkey {
    private int id;
    private int iq;
    private String name;
    private ArrayList<Integer> bananas;

    public Monkey() {
        id = 0;
        iq = 0;
        name = "dumb monke";
        bananas = new ArrayList<>(Arrays.asList(1, 2, 3));
    }

    public Monkey(int id, int iq, String name, ArrayList<Integer> bananas) {
        this.id = id;
        this.iq = iq;
        this.name = name;
        this.bananas = bananas;
    }


    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * @param iq
     */
    public void setIq(int iq) {
        this.iq = iq;
    }


    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return int
     */
    public int getId() {
        return id;
    }


    /**
     * @return int
     */
    public int getIq() {
        return iq;
    }


    /**
     * @return String
     */
    public String getName() {
        return name;
    }


    /**
     * @param banana
     */
    public void addBanana(int banana) {
        this.bananas.add(banana);
    }


    /**
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getBanana() {
        return bananas;
    }
}

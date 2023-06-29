package com.codecool.dungeoncrawl.logic;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Save {
    private long id;
    private Timestamp saveTime;
    private String savesStatus;
}


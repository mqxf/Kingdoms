package com.kingdomsofargus.kingdoms.tasks;

import com.kingdomsofargus.kingdoms.Kingdoms;
import org.bukkit.scheduler.BukkitRunnable;

public class BackupTask extends BukkitRunnable {

    private Kingdoms core;

    public BackupTask(Kingdoms core) {
        this.core = core;
    }

    @Override
    public void run() {
        System.out.println("Test");
        Kingdoms.getCore().getUserManager().saveUsers();
        Kingdoms.getCore().getUserManager().getUsers().clear();
        System.out.println("[Kingdoms] A quick backup was saved -- Database updated");
    }
}

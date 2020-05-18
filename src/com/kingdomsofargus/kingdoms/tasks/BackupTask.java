package com.kingdomsofargus.kingdoms.tasks;

import com.kingdomsofargus.kingdoms.Kingdoms;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Level;

public class BackupTask
        extends BukkitRunnable
{
    private Kingdoms core;

    public BackupTask(Kingdoms core) {
        this.core = core;
    }

    @Override
    public void run() {
        core.getUserManager().saveUsers();
        core.getUserManager().getUsers().clear();
        core.getServer().getLogger().log(Level.INFO, "[Kingdoms] A quick backup was saved -- Database updated");
    }
}

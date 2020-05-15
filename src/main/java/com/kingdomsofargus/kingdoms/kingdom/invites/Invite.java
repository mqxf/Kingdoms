package com.kingdomsofargus.kingdoms.kingdom.invites;

import java.util.UUID;

public class Invite {

    private int invitedToKingdom;
    private UUID uuid; /** Player Invited **/

    public Invite(int invitedToKingdom, UUID uuid) {
        this.invitedToKingdom = invitedToKingdom;
        this.uuid = uuid;
    }

    public int getInvitedToKingdom() {
        return invitedToKingdom;
    }

    public void setInvitedToKingdom(int invitedToKingdom) {
        this.invitedToKingdom = invitedToKingdom;
    }

    public String getUUID() {
        return uuid.toString();
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }
}

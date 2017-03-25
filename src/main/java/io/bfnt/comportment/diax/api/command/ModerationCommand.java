package io.bfnt.comportment.diax.api.command;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.managers.GuildController;

/**
 * Created by Comporment on 25/03/2017 at 09:26
 * Dev'ving like a sir since 1998. | https://github.com/Comportment
 */
public abstract class ModerationCommand extends DiaxCommand
{
    public void punish(Member member, MessageChannel channel, Punishment punishment)
    {
        GuildController c = member.getGuild().getController();
        Message message = makeMessage(punishment.name(), String.format("%s has been %s.", getNiceName(member.getUser()), punishment.getPast())).build();
        switch (punishment)
        {
            case BAN:
            {
                c.ban(member, 1).queue();
                return;
            }
            case KICK:
            {
                c.kick(member).queue();
                return;
            }
            case SOFTBAN:
            {
                c.ban(member, 1).queue();
                c.unban(member.getUser()).queue();
                return;
            }
        }
        channel.sendMessage(message).queue();
    }
}
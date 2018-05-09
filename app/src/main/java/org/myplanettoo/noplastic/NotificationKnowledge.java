package org.myplanettoo.noplastic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotificationKnowledge {
    static List<NotificationItem> notificationItems = new ArrayList<>();

    static {
        notificationItems.add(
                NotificationItem
                        .create()
                        .setType(NotificationItem.MORNING_TYPE)
                        .setTitle("Čo že???")
                        .setDescription("Ročne vyprodukujeme 4 miliardy ton odpadkov. Je to ok?")
        );

        notificationItems.add(
                NotificationItem
                        .create()
                        .setType(NotificationItem.MORNING_TYPE)
                        .setTitle("Chráň tulene")
                        .setDescription("Dnes môže zomrieť 10 tuleňov.")
        );

        notificationItems.add(
                NotificationItem
                        .create()
                        .setType(NotificationItem.MORNING_TYPE)
                        .setTitle("Stromy - pľúca planéty")
                        .setDescription("Jedným vyrubaným stromom príde o kyslík viac ako 70 ľudí.")
        );

        notificationItems.add(
                NotificationItem
                        .create()
                        .setType(NotificationItem.MORNING_TYPE)
                        .setTitle("Si priemerný Slovák?")
                        .setDescription("Priemerný Slovák vyprodukuje do dňa 1.3 kg odpadu. Nebuď priemerný Slovák.")
        );

        notificationItems.add(
                NotificationItem
                        .create()
                        .setType(NotificationItem.MORNING_TYPE)
                        .setTitle("Pôjdeš dnes na nákup?")
                        .setDescription("Taška stojí asi 3 centy. Zober si vlastnú.")
        );

        notificationItems.add(
                NotificationItem
                        .create()
                        .setType(NotificationItem.MORNING_TYPE)
                        .setTitle("Dnes bude horúco")
                        .setDescription("Vodu si naber do vlastnej fľaše. Nekupuj plastové.")
        );

        notificationItems.add(
                NotificationItem
                        .create()
                        .setType(NotificationItem.MORNING_TYPE)
                        .setTitle("Rýchla sprcha")
                        .setDescription("Čo sa tam budeš močkať? Pri jednom sprchovaní sa spotrebuje cca. 180l pitnej vody.")
        );

        notificationItems.add(
                NotificationItem
                        .create()
                        .setType(NotificationItem.MORNING_TYPE)
                        .setTitle("Bio odpad z raňajok?")
                        .setDescription("Pozri ako si môžeš založiť kompost.")
        );
    }

    static NotificationItem getRandomByType(String type) {
        List<NotificationItem> flist = new ArrayList<>();
        for (NotificationItem notificationItem : notificationItems) {
            if (notificationItem.getType().equals(type)) {
                flist.add(notificationItem);
            }
        }
        Random random = new Random();

        return notificationItems.get(random.nextInt(flist.size()));
    }
}

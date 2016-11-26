package mk.com.mztransportad.makedonskizheleznici.Helpers;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by gjorgjim on 7/6/16.
 */
public class Data {
    public static List<Train> getTrainList() {
        List<Train> trainList = new ArrayList<>();

        List<Station> stationList = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 6);
        c.set(Calendar.MINUTE, 10);
        stationList.add(new Station(c, "Велес"));
        Station start = new Station(c, "Велес");

        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.HOUR_OF_DAY, 6);
        c2.set(Calendar.MINUTE, 35);
        stationList.add(new Station(c2, "Зелениково"));

        Calendar c3 = Calendar.getInstance();
        c3.set(Calendar.HOUR_OF_DAY, 7);
        c3.set(Calendar.MINUTE, 0);
        stationList.add(new Station(c3, "Скопје"));
        Station end = new Station(c3, "Скопје");

        trainList.add(new Train(stationList, start, end));

        List<Station> stationList2 = new ArrayList<>();
        Calendar c4 = Calendar.getInstance();
        c4.set(Calendar.HOUR_OF_DAY, 11);
        c4.set(Calendar.MINUTE, 50);
        stationList2.add(new Station(c4, "Велес"));
        Station start2 = new Station(c4, "Велес");

        Calendar c5 = Calendar.getInstance();
        c5.set(Calendar.HOUR_OF_DAY, 12);
        c5.set(Calendar.MINUTE, 25);
        stationList2.add(new Station(c5, "Зелениково"));

        Calendar c6 = Calendar.getInstance();
        c6.set(Calendar.HOUR_OF_DAY, 12);
        c6.set(Calendar.MINUTE, 40);
        stationList2.add(new Station(c6, "Скопје"));
        Station end2 = new Station(c6, "Скопје");

        trainList.add(new Train(stationList2, start2, end2));

        List<Station> stationList3 = new ArrayList<>();
        Calendar c7 = Calendar.getInstance();
        c7.set(Calendar.HOUR_OF_DAY, 19);
        c7.set(Calendar.MINUTE, 45);
        stationList3.add(new Station(c7, "Велес"));
        Station start3 = new Station(c7, "Велес");

        Calendar c8 = Calendar.getInstance();
        c8.set(Calendar.HOUR_OF_DAY, 20);
        c8.set(Calendar.MINUTE, 10);
        stationList3.add(new Station(c8, "Зелениково"));

        Calendar c9 = Calendar.getInstance();
        c9.set(Calendar.HOUR_OF_DAY, 20);
        c9.set(Calendar.MINUTE, 35);
        stationList3.add(new Station(c9, "Скопје"));
        Station end3 = new Station(c9, "Скопје");

        trainList.add(new Train(stationList3, start3, end3));

        return trainList;
    }

    public static List<Price> getPriceList() {
        List<Price> priceList = new ArrayList<>();

        priceList.add(new Price("Скопје", "Куманово", 87, 140));
        priceList.add(new Price("Скопје", "Тетово", 120, 192));
        priceList.add(new Price("Скопје", "Гостивар", 158, 252));
        priceList.add(new Price("Скопје", "Кичево", 208, 332));
        priceList.add(new Price("Скопје", "Велес", 113, 180));
        priceList.add(new Price("Скопје", "Штип", 198, 316));
        priceList.add(new Price("Скопје", "Кочани", 249, 398));
        priceList.add(new Price("Скопје", "Прилеп", 249, 398));
        priceList.add(new Price("Скопје", "Битола", 314, 502));
        priceList.add(new Price("Скопје", "Неготино", 198, 316));
        priceList.add(new Price("Скопје", "Гевгелија", 277, 444));
        priceList.add(new Price("Велес", "Прилеп", 158, 252));
        priceList.add(new Price("Велес", "Битола", 231, 370));
        priceList.add(new Price("Велес", "Гевгелија", 208, 332));
        priceList.add(new Price("Велес", "Кочани", 158, 252));
        priceList.add(new Price("Велес", "Штип", 113, 180));



        return priceList;
    }

    public static List<ParentTrain> getParentTrain() {
        List<ParentTrain> list = new ArrayList<>();
        list.add(new ParentTrain(new Station(null, "Велес"), new Station(null, "Скопје"), Data.getTrainList()));
        list.add(new ParentTrain(new Station(null, "Скопје"), new Station(null, "Велес"), Data.getTrainList()));
        list.add(new ParentTrain(new Station(null, "Скопје"), new Station(null, "Куманово"), Data.getTrainList()));

        return list;
    }
}

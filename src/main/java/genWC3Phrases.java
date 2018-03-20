import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Created by kschegolev on 03.03.2018.
 */
 public class genWC3Phrases {
   //парсит JSON фаил и возвращает случайное значения из массива в нем
    public static String getRandomePhrase(String race) throws Exception {

        String resultPhrase = "";
        String phrase = "";
        int persChois;
        String name = "";

        try {
            Object obj = new JSONParser().parse(new FileReader("warbot.json"));
            JSONObject jo = (JSONObject) obj;
            JSONArray ja = (JSONArray) jo.get(race);
            persChois = new Random().nextInt(ja.size());
            name = ja.get(persChois).toString().substring(ja.get(persChois).toString().indexOf('"') + 1, ja.get(persChois).toString().indexOf(':') - 1);
            JSONObject sjo = (JSONObject) ja.get(persChois);
            JSONArray sja = (JSONArray) sjo.get(name);
            phrase = sja.get(new Random().nextInt(sja.size())).toString();
            System.out.println(name + ": " + phrase);
            resultPhrase = name + ": " + phrase;
        } catch (Exception e) {
            System.out.println("Что то пошло не так..." + "\n" + e.toString());
            System.out.println("Выбран: " + name);
            System.out.println("Фраза: " + name);
        }

        return resultPhrase;
    }
}

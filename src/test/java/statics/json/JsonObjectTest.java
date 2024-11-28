package statics.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Random;

class JsonObjectTest {

    JsonObject getJsonObj(){
        JsonObject ob1 = new JsonObject();

        ob1.setField("string",new JsonValue<String>("eier lecken"));

        JsonValueList jvl2 = new JsonValueList();
        jvl2.add(new JsonValue<String>("str"));
        ob1.setField("stringlist", new JsonValue<JsonValueList>(jvl2));

        ob1.setField("double",new JsonValue<Double>(1_3.3_4d));
        ob1.setField("boolean",new JsonValue<Boolean>(true));

        JsonObject ob2 = new JsonObject();
        ob2.setField("s", new JsonValue<String>("EEEE"));
        ob1.setField("object",new JsonValue<JsonObject>(ob2));



        JsonValueList jvl1 = new JsonValueList();
        jvl1.add(new JsonValue<Double>(3d));
        jvl1.add(new JsonValue<Double>(30d));
        jvl1.add(new JsonValue<Double>(33d));
        jvl1.add(new JsonValue<Double>(32d));
        jvl1.add(new JsonValue<Double>(12d));
        ob2.setField("numlist", new JsonValue<JsonValueList>(jvl1));

        return ob1;
    }

    String getRandomString(){
        byte[] bytes = new byte[2+(int)Math.round(Math.random()*10)];
        new Random().nextBytes(bytes);

        String str = new String(bytes, StandardCharsets.UTF_8);
        return str.replace("\"","Ä").replace("\\","Ä").replace("\n","\\n");
    }

    JsonValue<?> getRandomJsonValue(boolean pGenObjects, int pDepth, boolean pGenList){
        double typeBase = Math.random()*75+(pGenObjects?25:0)+(pGenList?25:0);

        if(typeBase<25){
            return new JsonValue<>(getRandomString());
        }
        if(typeBase<50){
            int decimals = (int)Math.round(Math.random()*5);
            return new JsonValue<>((double)Math.round((Math.random()*1000)*decimals)/decimals);
        }
        if(typeBase<76||(!pGenList&&!pGenObjects)){
            return new JsonValue<>(Math.random()*100<50);
        }
        if((typeBase<101||!pGenObjects)&&pGenList){
            int size = (int)Math.round(Math.random()*5);
            JsonValueList list = new JsonValueList();
            list.add(getRandomJsonValue(pDepth>2, pDepth+1, false));
            Class<?> type = list.getAll()[0].get().getClass();
            for (int i = 0; i < size; i++) {
                JsonValue<?> next;
                do{
                    next = getRandomJsonValue(pDepth>2, pDepth-1, false);
                }while(!next.get().getClass().equals(type));
                list.add(next);
            }
            return new JsonValue<>(list);
        }else{
            return new JsonValue<>(getRandomJsonObject(pDepth-1));
        }
    }

    JsonObject getRandomJsonObject(int pDepth){
        JsonObject ret = new JsonObject();
        int fieldCount = (int)Math.round(Math.random()*7);

        for (int i = 0; i < fieldCount; i++) {
            ret.setField(getRandomString(),getRandomJsonValue(pDepth > 2,pDepth,true));
        }

        return ret;
    }
    @Test
    void testToString() {
        JsonObject jo = getRandomJsonObject(4);
        //System.out.println(jo.toString());
    }

    @Test
    void testFromString(){
        for (int i = 0; i < 10; i++) {
            JsonObject ob = getRandomJsonObject(3);
            String obs = ob.toString();
            System.out.println("##########################################before");
            System.out.println(obs);
            System.out.println("###########################################after");
            String obsAfter = JsonObject.fromString(obs).toString();
            System.out.println(obsAfter);

            Assertions.assertEquals(obs,obsAfter);
        }
    }
}
package statics;

import statics.json.JsonObject;

//todo comment
public class Main {
    public static void main(String[] args) {
        //todo file backups, option to not change default path if started with Argument
        //todo parseArgs


        JsonObject ob1 = new JsonObject();

        ob1.setField("string",new JsonObject.JsonValue<String>("eier lecken"));
        ob1.setField("double",new JsonObject.JsonValue<Double>(1_3.3_4d));
        ob1.setField("boolean",new JsonObject.JsonValue<Boolean>(true));

        JsonObject ob2 = new JsonObject();
        ob2.setField("s", new JsonObject.JsonValue<String>("EEEE"));
        ob1.setField("object",new JsonObject.JsonValue<JsonObject>(ob2));

        System.out.println(ob1.toString());
    }
}
package statics.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonObjectTest {

    @Test
    void testToString() {
        JsonObject ob1 = new JsonObject();

        ob1.setField("string",new JsonObject.JsonValue<String>("eier lecken"));
        ob1.setField("double",new JsonObject.JsonValue<Double>(1_3.3_4d));
        ob1.setField("boolean",new JsonObject.JsonValue<Boolean>(true));

        JsonObject ob2 = new JsonObject();
        ob2.setField("s", new JsonObject.JsonValue<String>("EEEE"));
        ob1.setField("object",new JsonObject.JsonValue<JsonObject>(ob2));

        JsonObject.JsonValueList jvl = new JsonObject.JsonValueList();
        jvl.add(new JsonObject.JsonValue<Double>(3d));
        jvl.add(new JsonObject.JsonValue<Double>(30d));
        jvl.add(new JsonObject.JsonValue<Double>(33d));
        jvl.add(new JsonObject.JsonValue<Double>(32d));
        jvl.add(new JsonObject.JsonValue<Double>(12d));
        ob2.setField("numlist", new JsonObject.JsonValue<JsonObject.JsonValueList>(jvl));
        System.out.println(ob1.toString());
    }
}
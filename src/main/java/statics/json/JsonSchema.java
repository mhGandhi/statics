package statics.json;

//Todo comment
public class JsonSchema extends JsonObject{
    public JsonSchema(String pSchema){
        this(JsonObject.fromString(pSchema));
    }
    public JsonSchema(JsonObject pSchema){
        for (String key:pSchema.getFieldKeys()){
            this.setField(key,pSchema.getField(key));
        }
    }
}


package statics.json;

/**
 * shell for values of json fields
 *
 * @param <T> type of the value
 *
 * @author Adrian Akipi
 */
public class JsonValue<T> {
    /**
     * value held by the associated field
     */
    private T value;

    public static JsonValue<?> fromString(String valueSt) {
        if (valueSt.startsWith("[") && valueSt.endsWith("]")) {
            return new JsonValue<>(JsonValueList.fromString(valueSt));
        }
        if (valueSt.startsWith("{") && valueSt.endsWith("}")) {
            return new JsonValue<>(JsonObject.fromString(valueSt));
        }
        if (valueSt.startsWith("\"") && valueSt.endsWith("\"") && valueSt.length() > 1) {
            return new JsonValue<>(valueSt.substring(1, valueSt.length() - 1));
        }
        if ((valueSt.equals("true") || valueSt.equals("false"))) {
            boolean res;
            res = valueSt.equals("true");
            return new JsonValue<>(res);
        }

        try {
            return new JsonValue<>(Double.parseDouble(valueSt));
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException(valueSt + " not a valid value");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @return value
     */
    public T get() {
        return this.value;
    }

    /**
     * @param pValue new value
     * @throws UnsupportedValueTypeException thrown, if the WOMP //todo when instantiated no longer ambiguous?
     */
    public void set(T pValue) throws UnsupportedValueTypeException {
        if (!JsonObject.SUPPORTED_VALUE_TYPES.contains(pValue.getClass()))
            throw new UnsupportedValueTypeException("type " + pValue.getClass() + " not supported for json");
        this.value = pValue;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * constructor ensures the initial value is of a supported type defined in JsonObject
     *
     * @param pInitValue initial value; also defines type
     */
    public JsonValue(T pInitValue) {
        if (!JsonObject.SUPPORTED_VALUE_TYPES.contains(pInitValue.getClass()))
            throw new UnsupportedValueTypeException("type " + pInitValue.getClass() + " not supported for json");
        this.value = pInitValue;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.toString(0);
    }

    /**
     * @param pIndent indent to add to the string if it represents an object
     * @return string
     */
    public String toString(int pIndent) {
        String ret = null;

        if (this.get().getClass().equals(String.class)) {
            ret = '"' + this.get().toString() + '"';
        }
        if (this.get() instanceof JsonObject tmp) {
            ret = '\n' + tmp.toString(pIndent + 1);
        }
        if (this.get() instanceof JsonValueList tmp) {
            ret = '\n' + tmp.toString(pIndent + 1);
        }
        if (ret == null) {
            ret = this.get().toString();
        }

        return ret;
    }
}

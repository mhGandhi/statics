package statics.app.view.components;

public enum ComponentLayers {
    BACKGROUND(1),
    NODES(2),
    EDGES(3),
    OVERLAY0(4);

    private final int layer;

    ComponentLayers(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }
}


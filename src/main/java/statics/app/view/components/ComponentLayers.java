package statics.app.view.components;

public enum ComponentLayers {
    NODES(1),
    EDGES(2),
    OVERLAY(3);

    private final int layer;

    ComponentLayers(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }
}


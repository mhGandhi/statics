package statics.app.view.components.nodes;

import statics.app.model.nodes.Joint;
import statics.app.view.ViewState;

public class JointComponent extends NodeComponent {
    public JointComponent(ViewState pVs, Joint n) {
        super(pVs, n.getPos());
    }
}

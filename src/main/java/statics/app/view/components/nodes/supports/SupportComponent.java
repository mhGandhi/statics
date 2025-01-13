package statics.app.view.components.nodes.supports;

import statics.app.model.nodes.Joint;
import statics.app.view.ViewState;
import statics.app.view.components.nodes.JointComponent;

public abstract class SupportComponent extends JointComponent {
    public SupportComponent(ViewState pVs, Joint n) {
        super(pVs, n);
    }
}

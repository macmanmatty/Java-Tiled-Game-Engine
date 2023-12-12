package com.jessematty.black.tower.Generators.Components;

import com.badlogic.ashley.core.Component;

public interface ExternalComponentDTO {
    public Class<? extends Component> getComponentClass();

    public void setComponentClass(Class<? extends Component> componentClass);

    public String getId();

    public void setId(String id);
}

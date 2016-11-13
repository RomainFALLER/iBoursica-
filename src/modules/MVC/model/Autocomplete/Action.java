package modules.MVC.model.Autocomplete;

import java.util.List;

//Created by Gfitas.
public abstract class Action {
    public abstract List<? extends Object> methodForGettingItem(String search);
    public abstract void methodWhenAnItemIsSelected(Object object) throws Exception;
}

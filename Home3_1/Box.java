package ru.geekbrains.homework3_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box <T extends Fruits>{

    private List <T> fruitBox;


    public Box (T... fruit) {
        this.fruitBox = new ArrayList(Arrays.asList(fruit));
    }


    public float getWeight () {
        float wei = 0.0f;
        for (T fruit : fruitBox) {
            wei += fruit.getWeight();
        }
        return wei;
    }

    public boolean compare (Box <?> comparable) {
        return Math.abs(this.getWeight() - comparable.getWeight()) < 0.001;
    }

    public void remove (Box <? super T> comparable) {
        if (comparable == this) {
            return;
        }
        comparable.fruitBox.addAll(this.fruitBox);
        this.fruitBox.clear();
    }

    public void add (T fruit) {
        fruitBox.add(fruit);
    }

}

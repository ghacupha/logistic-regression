package io.github.ghacupha.logit;

import java.util.Arrays;
import java.util.Objects;

public class Instance {

    private int instanceLabel;
    private int[] instanceDataArray;

    Instance(int label, int[] instanceDataArray) {
        this.instanceLabel = label;
        this.instanceDataArray = instanceDataArray;
    }

    public int getInstanceLabel() {
        return instanceLabel;
    }

    public int[] getInstanceDataArray() {
        return instanceDataArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Instance instance = (Instance) o;
        return instanceLabel == instance.instanceLabel && Arrays.equals(instanceDataArray, instance.instanceDataArray);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instanceLabel, instanceDataArray);
    }
}

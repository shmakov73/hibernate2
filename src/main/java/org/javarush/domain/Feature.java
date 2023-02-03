package org.javarush.domain;

public enum Feature {

    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String value;
    Feature(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Feature getFeatureByValue(String value){
        Feature[] features = Feature.values();
        Feature returnValue = null;
        for (Feature feature : features) {
            if (feature.value.equals(value))
                returnValue =  feature;
        }
        return returnValue;
    }
}

package com.industriousgnomes.dnd.character.feature;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import com.industriousgnomes.dnd.character.Source;

public class Features {

    private List<Feature> features = new LinkedList<>();

    // ======= Source =======
    public Collection<Feature> getAll(Source source) {
        Collection<Feature> resultFeatures = new LinkedList<>();

        source(source).forEach(e -> resultFeatures.add(e));

        return resultFeatures;
    }

    public void remove(Source source) {
        Collection<Feature> removeFeatures = new LinkedList<>();

        source(source).forEach(e -> removeFeatures.add(e));

        features.removeAll(removeFeatures);
    }

    private Stream<Feature> source(Source source) {
        return features.stream().filter(f -> f.getSource() == source);
    }

    // ======= FeatureType =======
    public Collection<Feature> getAll(FeatureType featureType) {
        Collection<Feature> resultFeatures = new LinkedList<>();

        relevant(featureType).forEach(e -> resultFeatures.add(e));

        return resultFeatures;
    }

    public void add(Feature feature) {
        this.features.add(feature);
    }

    public void addAll(Collection<Feature> features) {
        this.features.addAll(features);
    }

    public int total(FeatureType featureType) {
        return relevant(featureType).mapToInt(x -> (Integer) x.execute(featureType)).sum();
    }

    public int count(FeatureType featureType) {
        return (int) relevant(featureType).count();
    }

    public boolean contains(FeatureType featureType) {
        return count(featureType) > 0;
    }

    public void remove(FeatureType featureType) {
        Collection<Feature> removeFeatures = new LinkedList<>();

        relevant(featureType).forEach(e -> removeFeatures.add(e));

        features.removeAll(removeFeatures);
    }

    public void removeLast(FeatureType featureType) {
        Object[] featureArray = relevant(featureType).toArray();
        if (featureArray.length > 0) {
            features.remove(featureArray[featureArray.length - 1]);
        }
    }

    private Stream<Feature> relevant(FeatureType featureType) {
        return features.stream().filter(f -> f.isRelevant(featureType));
    }

}

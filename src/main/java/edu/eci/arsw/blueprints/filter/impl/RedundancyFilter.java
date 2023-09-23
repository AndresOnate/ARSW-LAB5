package edu.eci.arsw.blueprints.filter.impl;

import edu.eci.arsw.blueprints.filter.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Component
public class RedundancyFilter implements BlueprintFilter {

    @Override
    public Blueprint filterBlueprint(Blueprint bp) {
        List<Point> points = bp.getPoints();
        if(points.size() == 1){
            return bp;
        }
        List<Point> filteredPoints = new ArrayList<>();
        int pointsSize = points.size();
        for (int i = 0; i < points.size() -1; i++) {
            Point currentPoint = points.get(i);
            Point nextPoint = points.get(i + 1);
            if (!currentPoint.equals(nextPoint)) {
                filteredPoints.add(currentPoint);
            }
            if( (i == pointsSize-2) && (pointsSize% 3 == 0)){
                filteredPoints.add(points.get(pointsSize-1));
            }
        }

        bp.setPoints(filteredPoints);
        return bp;
    }

    @Override
    public Set<Blueprint> filterBlueprints(Set<Blueprint> blueprints) {
        Set<Blueprint> filteredBlueprints = new HashSet<>();
        for (Blueprint bp : blueprints) {
            Blueprint filteredBlueprint = filterBlueprint(bp);
            filteredBlueprints.add(filteredBlueprint);
        }
        return filteredBlueprints;
    }
}

package com.school.tanksgame;

import processing.core.PVector;

public class Collision {
    // LINE/CIRCLE
    public static boolean lineCircle(float x1, float y1, float x2, float y2, float cx, float cy, float r) {

        // get length of the line
        float distX = x1 - x2;
        float distY = y1 - y2;
        float len = (float) Math.sqrt( (distX*distX) + (distY*distY) );

        // get dot product of the line and circle
        float dot = (float) (( ((cx-x1)*(x2-x1)) + ((cy-y1)*(y2-y1)) ) / Math.pow(len,2));

        // find the closest point on the line
        float closestX = x1 + (dot * (x2-x1));
        float closestY = y1 + (dot * (y2-y1));

        // is this point actually on the line segment?
        // if so keep going, but if not, return false
        boolean onSegment = linePoint(x1,y1,x2,y2, closestX,closestY);
        if (!onSegment) return false;

        // optionally, draw a circle at the closest
        // point on the line
        // get distance to closest point
        distX = closestX - cx;
        distY = closestY - cy;
        float distance = (float) Math.sqrt( (distX*distX) + (distY*distY) );

        if (distance <= r) {
            return true;
        }
        return false;
    }


    // LINE/POINT
    public static boolean linePoint(float x1, float y1, float x2, float y2, float px, float py) {

        // get distance from the point to the two ends of the line
        float d1 = dist(px,py, x1,y1);
        float d2 = dist(px,py, x2,y2);

        // get the length of the line
        float lineLen = dist(x1,y1, x2,y2);

        // since floats are so minutely accurate, add
        // a little buffer zone that will give collision
        float buffer = (float) 0.1;    // higher # = less accurate

        // if the two distances are equal to the line's
        // length, the point is on the line!
        // note we use the buffer here to give a range,
        // rather than one #
        if (d1+d2 >= lineLen-buffer && d1+d2 <= lineLen+buffer) {
            return true;
        }
        return false;
    }
    //projection af vektorer på vektorer
    public static PVector projection(PVector vec1, PVector vec2) {
        return PVector.mult(vec2, (float) (PVector.dot(vec1, vec2) / Math.pow(vec2.mag(), 2)));
    }
    public static float dist(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    public static float dist(PVector loc1, PVector loc2) {
        return (float) Math.sqrt(Math.pow(loc2.x - loc1.x, 2) + Math.pow(loc1.y - loc2.y, 2));
    }
}
package com.example.test;

abstract class CustomView {
    private int id;
    abstract int getChildCount();
    abstract CustomView getChildAt(int index);

    CustomView findViewById(int viewId){
        if(viewId == id) {
            return this;
        }

        for(int i = 0 ; i < getChildCount(); i++){
            if(getChildAt(i).id == viewId) {
                return getChildAt(i);
            }else{
                getChildAt(i).findViewById(viewId);
            }
        }

        return null;

    }
}





/**
 * Given a root node of CustomView, return the CustomView that matches the given id in
 * findViewById method
 */

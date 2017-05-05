package com.nineinchmales.longcircuit.Helpers;

import com.nineinchmales.longcircuit.Views.Cell;

import java.util.ArrayList;

/**
 * Created by Rohan Gorawala on 5/4/2017.
 */

public class Connection {
    ArrayList<Cell> cells = new ArrayList<>();

    public Connection(Cell a, Cell b){
        cells.add(a);
        cells.add(b);
    }

    public ArrayList<Cell> getCells(){
        return cells;
    }
}

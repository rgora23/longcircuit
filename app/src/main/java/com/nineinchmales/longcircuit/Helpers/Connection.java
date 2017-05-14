package com.nineinchmales.longcircuit.Helpers;

import com.nineinchmales.longcircuit.Views.Cell;
import com.nineinchmales.longcircuit.Views.Node;

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

    public Cell.Color getColor() {
        return getFirstCell().getColor();
    }

    public ArrayList<Cell> getCells(){
        return cells;
    }

    public void addCell(Cell cell){
        cells.add(cell);
        cell.setConnection(this);
    }

    private Node getFirstCell() {
        return (Node) cells.get(0);
    }
}

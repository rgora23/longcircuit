package com.nineinchmales.longcircuit.Views;

import android.content.Context;

import com.nineinchmales.longcircuit.Helpers.Connection;

/**
 * Created by Rohan Gorawala on 5/4/2017.
 */

public class Node extends Cell {
    Color color;

    public Node(Context context, int column, int row) {
        super(context, column, row);
    }

    public Node(Context context, int column, int row, Color color) {
        this(context, column, row);
        this.color = color;
    }

    public void connectTo(Cell cell){
        if(!this.hasConnection() && !cell.hasConnection()) {
            connection = new Connection(this, cell);

            cell.setConnection(connection);
        }
    }

    public Color getColor() {
        return color;
    }

}

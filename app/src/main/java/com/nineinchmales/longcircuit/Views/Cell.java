package com.nineinchmales.longcircuit.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.nineinchmales.longcircuit.Helpers.Connection;
import com.nineinchmales.longcircuit.Helpers.Position;

public class Cell extends View {
    Connection connection;
    Position position;

    public Cell(Context context, int column, int row) {
        super(context);
        position = new Position(column, row);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void connectTo(Cell cell){
        if (cell.getConnection() == null) {
            connection = new Connection(this, cell);
        }
    }

    public Connection getConnection(){
        return connection;
    }
}

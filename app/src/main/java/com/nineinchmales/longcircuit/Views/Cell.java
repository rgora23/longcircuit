package com.nineinchmales.longcircuit.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.nineinchmales.longcircuit.Helpers.Connection;
import com.nineinchmales.longcircuit.Helpers.Position;

public class Cell extends View {
    Connection connection;
    Position position;

    public enum Color {
        NONE, RED, GREEN, BLUE
    }

    public Cell(Context context, int column, int row) {
        super(context);
        position = new Position(column, row);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void connectTo(Cell cell){
        if (canConnectTo(cell)) {
            connection.addCell(cell);
        }
    }

    public Color getColor() {
        return hasConnection() ? getConnection().getColor() : Color.NONE;
    }

    public boolean hasConnection(){
        return getConnection() != null;
    }

    public Connection getConnection(){
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private boolean canConnectTo(Cell cell) {
        return hasConnection() && canConnectBasedOnColor(cell);
    }

    private boolean hasColor() {
        return getColor() != Color.NONE;
    }

    private boolean canConnectBasedOnColor(Cell cell) {
        return !cell.hasColor() || getColor() == cell.getColor();
    }
}

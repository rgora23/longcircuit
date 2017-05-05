package com.nineinchmales.longcircuit.Views;

import android.content.Context;

import com.nineinchmales.longcircuit.Activities.MainActivity;
import com.nineinchmales.longcircuit.BuildConfig;
import com.nineinchmales.longcircuit.Helpers.Connection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class CellTest {
    @Test
    public void nodeConnectingToDetachedAdjacentCell_shouldCreateConnection() {
        Context context = generateContext();
        Node node = new Node(context, 0, 0);
        Cell cell = new Cell(context, 1, 0);
        node.connectTo(cell);
        Connection connection = node.getConnection();
        Assert.assertNotNull(connection);
        ArrayList<Cell> cells = connection.getCells();
        Assert.assertEquals(cells.get(0), node);
        Assert.assertEquals(cells.get(1), cell);
    }

    @Test
    public void nodeConnectingToAttachedAdjacentCell_shouldNotCreateConnection() {
        Context context = generateContext();
        Node node = new Node(context, 0, 0);
        Cell cell = new Cell(context, 1, 0);
        Cell connectedCell = new Cell(context, 2, 0);
        cell.connectTo(connectedCell);
        node.connectTo(cell);
        Connection connection = node.getConnection();
        Assert.assertNull(connection);
    }

//    Todo This should be using a factory activity
    private Context generateContext() {
        return Robolectric.buildActivity(MainActivity.class).create().get();
    }


}
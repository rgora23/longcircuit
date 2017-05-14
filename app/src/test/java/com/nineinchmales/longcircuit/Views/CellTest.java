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
import org.robolectric.util.Logger;

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
        Node node1 = new Node(context, 0, 0);
        Cell cell = new Cell(context, 1, 0);
        Node node2 = new Node(context, 2, 0);
        node2.connectTo(cell);
        Logger.debug("node1 connection is null?", node1.getConnection() == null);
        node1.connectTo(cell);
        Connection connection = node1.getConnection();
        Assert.assertNull(connection);
    }

    @Test
    public void cellWithoutConnectionConnectingToAdjacentCell_shouldNotCreateConnection() {
        Context context = generateContext();
        Cell cell1 = new Cell(context, 0, 0);
        Cell cell2 = new Cell(context, 1, 0);
        cell1.connectTo(cell2);
        Assert.assertNull(cell1.getConnection());
    }

    @Test
    public void cellWithConnectionConnectingToAdjacentCell_shouldCreateConnection() {
        Context context = generateContext();
        Node node1 = new Node(context, 0, 0);
        Cell cell1 = new Cell(context, 1, 0);
        Cell cell2 = new Cell(context, 2, 0);
        node1.connectTo(cell1);
        cell1.connectTo(cell2);
        Assert.assertNotNull(cell2.getConnection());
    }

    @Test
    public void extendingConnectionOnCell_shouldAddCellToConnectionList() {
        Context context = generateContext();
        Node node1 = new Node(context, 0, 0);
        Cell cell1 = new Cell(context, 1, 0);
        Cell cell2 = new Cell(context, 2, 0);
        node1.connectTo(cell1);
        cell1.connectTo(cell2);
        ArrayList<Cell> cellsInConnection = node1.getConnection().getCells();
        Assert.assertEquals(cell2, cellsInConnection.get(2));
    }

    @Test
    public void initiatingConnectionOnNodeWithExistingConnection_shouldNotCreateConnection() {
        Context context = generateContext();
        Node node1 = new Node(context, 0, 0);
        Cell cell1 = new Cell(context, 1, 0);
        Cell cell2 = new Cell(context, 0, 1);
        node1.connectTo(cell1);
        node1.connectTo(cell2);
        Assert.assertNull(cell2.getConnection());
    }

    @Test
    public void initiatingConnectionOnNodeFromCell_shouldCreateConnection() {
        Context context = generateContext();
        Node node1 = new Node(context, 0, 0);
        Cell cell1 = new Cell(context, 1, 0);
        Node node2 = new Node(context, 2, 0);
        node1.connectTo(cell1);
        cell1.connectTo(node2);
        ArrayList<Cell> cells = node1.getConnection().getCells();
        Assert.assertEquals(cells.get(0), node1);
        Assert.assertEquals(cells.get(1), cell1);
        Assert.assertEquals(cells.get(2), node2);
    }

    @Test
    public void initiatingConnectionWithDifferentColoredNode_shouldNotCreateConnection(){
        Context context = generateContext();
        Node node1 = new Node(context, 0,0, Cell.Color.RED);
        Cell cell = new Cell(context, 1, 0);
        Node node2 = new Node(context, 2,0, Cell.Color.GREEN);
        node1.connectTo(cell);
        cell.connectTo(node2);
        Assert.assertFalse(node2.hasConnection());
    }



    // TODO test on the positions (non adjacent should not connect)
    // TODO test that different colors cannot create nodes

//    Todo This should be using a factory activity
    private Context generateContext() {
        return Robolectric.buildActivity(MainActivity.class).create().get();
    }
}
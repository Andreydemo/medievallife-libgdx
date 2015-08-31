package com.demosoft.game.medievallife.core;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class IsometricCamera extends OrthographicCamera {
	private final static Matrix4 gridToWorld;
	private final static Matrix4 worldToGrid;

	static {
		gridToWorld = new Matrix4(new float[] { 8f, -4f, 0f, 0f, 8f, 4f, 0f, 0f, 0f, 0f, 1f, 0f, 0, 0, 0f, 1f });
		worldToGrid = new Matrix4(gridToWorld).inv();
	}

	public static void worldToGrid(Vector3 worldCoordinate) {
		if (worldCoordinate.z != 0) {
			Vector3 v = new Vector3(0, worldCoordinate.z*2, 0);
			v.mul(gridToWorld);
			v.y-=540;
			worldCoordinate.y += v.y;
			worldCoordinate.mul(worldToGrid);
		} else {
			worldCoordinate.mul(worldToGrid);
		}
	}

	public static void gridToWorld(Vector3 gridCoordinate) {
		if (gridCoordinate.z != 0) {
			Vector3 v = new Vector3(0, gridCoordinate.z*2, 0);
			v.mul(gridToWorld);
			v.y-=540;
			gridCoordinate.mul(gridToWorld);
			gridCoordinate.y -= v.y;
		} else {
			gridCoordinate.mul(gridToWorld);
		}
	}

	public static Vector3 getGridToWorld(Vector3 gridCoordinate){
		Vector3 worldPositon = new Vector3(gridCoordinate);
		IsometricCamera.gridToWorld(worldPositon);
		return worldPositon;
	}

	public static Vector3 getSimpleVec(Vector3 v) {
		return new Vector3(v).add(1080f, -540, 0).scl(0.025f, 0.025f, 1);
	}

	public static Vector3[] getSimpleVec(Vector3... v) {
		ArrayList<Vector3> arr = new ArrayList<Vector3>();

		for (Vector3 vector3 : v) {
			arr.add(getSimpleVec(vector3));
		}
		Vector3[] retArr = new Vector3[arr.size()];
		arr.toArray(retArr);
		return retArr;
	}

	public static void main(String[] args) {
		Vector3 p1 = new Vector3(0, 0, 0);
		Vector3 p2 = new Vector3(10, 0, 0);
		Vector3 p3 = new Vector3(0, 10, 0);
		Vector3 p4 = new Vector3(10, 10, 0);

		Vector3 p11 = new Vector3(0, 0, 10);
		Vector3 p21 = new Vector3(10, 0, 10);
		Vector3 p31 = new Vector3(0, 10, 10);
		Vector3 p41 = new Vector3(10, 10, 10);
		
		System.out.println(p1 + " " + p2 + " " + p3 + " " + p4);
		System.out.println(p11 + " " + p21 + " " + p31 + " " + p41);
		System.out.println();
		gridToWorld(p1);
		gridToWorld(p2);
		gridToWorld(p3);
		gridToWorld(p4);
		gridToWorld(p11);
		gridToWorld(p21);
		gridToWorld(p31);
		gridToWorld(p41);
		System.out.println(p1 + " " + p2 + " " + p3 + " " + p4);
		System.out.println("simp" + Arrays.deepToString(getSimpleVec(p1,p2,p3,p4)));
		System.out.println();
		System.out.println(p11 + " " + p21 + " " + p31 + " " + p41);
		System.out.println("simp" + Arrays.deepToString(getSimpleVec(p11,p21,p31,p41)));
		System.out.println();

		worldToGrid(p1);
		worldToGrid(p2);
		worldToGrid(p3);
		worldToGrid(p4);
		worldToGrid(p11);
		worldToGrid(p21);
		worldToGrid(p31);
		worldToGrid(p41);
		System.out.println(p1 + " " + p2 + " " + p3 + " " + p4);
		System.out.println(p11 + " " + p21 + " " + p31 + " " + p41);
	}
}

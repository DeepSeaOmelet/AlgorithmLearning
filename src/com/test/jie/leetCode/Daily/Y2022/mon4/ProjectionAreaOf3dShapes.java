package com.test.jie.leetCode.Daily.Y2022.mon4;

public class ProjectionAreaOf3dShapes {
    public static void main(String[] args) {
        ProjectionAreaOf3dShapes projectionAreaOf3dShapes = new ProjectionAreaOf3dShapes();
        System.out.println(projectionAreaOf3dShapes.projectionArea(new int[][]{{1, 2}, {3, 4}}));
    }

    public int projectionArea(int[][] g) {
        //xy只需记录grid[i][j]不为零的个数
        //zx要记录二维数组每一行最大值之和
        //yz要记录二维数组每一列最大值之和
        int xy = 0, yz = 0, zx = 0;
        int len = g.length;
        for (int i = 0; i < len; i++) {
            int a = 0, b = 0;
            for (int j = 0; j < len; j++) {
                if (g[i][j] > 0) {
                    xy++;
                }
                a = Math.max(a, g[i][j]);//每一行最大
                b = Math.max(b, g[j][i]);//每一列最大
            }
            zx += a;
            yz += b;
        }
        return xy+zx+yz;
    }
}

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        int vl = matrix.length;
        if (vl == 0) {
            return 0;
        }
        int hl = matrix[0].length;
        int[] height = new int[hl];
        int[] lb = new int[hl];
        int[] rb = new int[hl];
        int max = 0;
        Arrays.fill(rb, hl);
        for (int i = 0; i < vl; i++) {
            // fill height
            for (int j = 0; j < hl; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                    lb[j] = 0;
                    rb[j] = hl;
                }
            }

            // determine left boundary.
            int lbcr = 0;
            for (int j = 0; j < hl; j++) {
                if (matrix[i][j] == '1') {
                    lb[j] = Math.max(lb[j], lbcr);
                } else {
                    lbcr = j + 1;
                    lb[j] = 0;
                }
            }
            // determine the right boundary.
            int rbcr = hl;
            for (int j = hl - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    rb[j] = Math.min(rb[j], rbcr);
                } else {
                    rbcr = j;
                    rb[j] = hl;
                }
            }
            // find the maximum area.
            for (int j = 0; j < hl; j++) {
                int area = (rb[j] - lb[j]) * height[j];
                if (area > max) {
                    max = area;
                }
            }
        }
        return max;
    }
}
public class Loader
{
    private static final int ROW_COUNT = 5;
    private static final int COLUMN_COUNT = 5;
    public static void main(String[] args) {
        String [][] stringsWithCross = new String [ROW_COUNT][COLUMN_COUNT];
        String space = " ";
        String cross = "x";
        for (int j = 0; j < ROW_COUNT; j++) {
            for (int i = 0; i < COLUMN_COUNT; i++) {
                if (j == i || Math.abs(j - i) == Math.abs((COLUMN_COUNT -1) - j*2)) {
                    stringsWithCross [j][i] = cross;
                    System.out.print(stringsWithCross [j][i]);
                }
                else {
                    stringsWithCross [j][i] = space;
                    System.out.print(stringsWithCross [j][i]);
                }
            }
            System.out.println();
        }

    }
}

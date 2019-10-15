package shebang;

import java.util.ArrayList;

public class FSMan {
    private static final ArrayList<FS> FS_ARRAY_LIST = new ArrayList<>();

    public static void createFS(String userID) {
        FS fs = new FS();
        fs.userID = userID;
        FS_ARRAY_LIST.add(fs);
    }

    public static ArrayList<File> getFS(String userID) {
        for (FS fs : FS_ARRAY_LIST) {
            if (fs.userID.equals(userID)) {
                return fs.files;
            }
        }
        return null;
    }

    static class FS {
        private String userID;
        private ArrayList<File> files = new ArrayList<>();
    }
}

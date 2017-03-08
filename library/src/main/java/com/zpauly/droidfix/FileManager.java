package com.zpauly.droidfix;

import java.io.File;

/**
 * Created by zpauly on 2017/3/8.
 */

public class FileManager {
    public static File getNativeLibraryFolder(String applicationId) {
        return new File("/data/data" + applicationId, "lib");
    }
}

package com.kindcat.archivemedo.guides;

import java.io.File;

/**
 *
 * @author dreamer
 * @version 1.0.0.15
 */
public class SuperUploadGuides {

    public boolean getExistsTempFolder(File tempDir) {
        ExistsTempFolder existsTempFolder = new ExistsTempFolder();
        return existsTempFolder.getExistsFolder(tempDir);
    }
}

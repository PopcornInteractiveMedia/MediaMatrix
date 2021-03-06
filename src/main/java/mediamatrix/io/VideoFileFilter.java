package mediamatrix.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class VideoFileFilter extends javax.swing.filechooser.FileFilter implements FilenameFilter {

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        String name = file.getName();
        String[] suffixes = {".flv", ".FLV", ".mp4", ".MP4"};
        for (int i = 0; i < suffixes.length; i++) {
            if (name.endsWith(suffixes[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean accept(File dir, String name) {
        String[] suffixes = {".flv", ".FLV", ".mp4", ".MP4"};
        for (int i = 0; i < suffixes.length; i++) {
            if (name.endsWith(suffixes[i])) {
                return true;
            }
        }
        return false;
    }

    public File[] filter(File[] files) {
        final List<File> list = new ArrayList<File>();
        for (int i = 0; i < files.length; i++) {
            if (accept(files[i])) {
                list.add(files[i]);
            }
        }
        return list.toArray(new File[list.size()]);
    }

    @Override
    public String getDescription() {
        return "Videoファイル (*.flv, *.mp4)";
    }
}

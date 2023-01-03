package com.vzsme.commonServiceLib.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DirectoryService {
    /**
     *
     * @param searchDir - Directory to search in
     * @param extension - extension of the file.
     * @return
     */
    public static File getFileFromDir(File searchDir, String extension)
    {
        File filesList[] = searchDir.listFiles();
        String jsonConfigFileName = "";

        for(File f : filesList) {
            if(f.isDirectory())
            {
                return getFileFromDir(f, extension);
            }else {
                if (f.getAbsoluteFile().getAbsolutePath().contains(extension)) {
                    return f;

                }
            }
        }
        return null;
    }

    /**
     *
     * @param searchDir - Directory to search in
     * @param extension - extension of the file.
     * @param partOfFileName - should contain in the filename
     * @return
     */
    public static File getFileFromDir(File searchDir, String extension, String partOfFileName)
    {
        File filesList[] = searchDir.listFiles();
        String jsonConfigFileName = "";

        for(File f : filesList) {
            if(f.isDirectory())
            {
                return getFileFromDir(f, extension, partOfFileName);
            }else {
                if (f.getAbsoluteFile().getAbsolutePath().contains(extension) && f.getAbsoluteFile().getAbsolutePath().contains(partOfFileName)) {
                    return f;

                }
            }
        }
        return null;
    }


    /**
     * Deletes oldest file(s) from directory that are over the limit.
     * @param parentFolder - Directory to delete files from.
     * @param filenameFilter - Files with this name filter should be removed in parent folder if over limit. If null then only limit will be left.
     * @param limit - Number of files, with this name, allowed in parent folder.
     * @return
     */
    public static void deleteOldFiles(Path parentFolder, String filenameFilter, int limit) {
        List<Path> files = getSortedFilesByDateCreated(parentFolder, filenameFilter, false);
        if (files.size()<=limit){
            return;
        }
        //delete recent files and keeping old files in the list
        files.subList(0, limit).clear();

        //deleting old files
        files.forEach(p -> {
            try {
                Files.delete(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static List<Path> getSortedFilesByDateCreated(Path parentFolder, String filenameFilter, boolean ascendingOrder) {
        try {
            Comparator<Path> pathComparator = Comparator.comparingLong(p -> getFileCreationEpoch((p).toFile()));
            return Files.list(parentFolder)
                    .filter(Files::isRegularFile)
                    .filter(p -> filenameFilter == null || p.getFileName().toString().matches(filenameFilter))
                    .sorted(ascendingOrder ? pathComparator : pathComparator.reversed())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static long getFileCreationEpoch(File file) {
        try {
            BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            return attr.creationTime().toInstant().toEpochMilli();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

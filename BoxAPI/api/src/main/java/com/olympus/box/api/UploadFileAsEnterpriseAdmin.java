package com.olympus.box.api;



import com.box.sdk.*;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class UploadFileAsEnterpriseAdmin {

    private static final String CLIENT_ID = "jwg191xpjewy82dnb201dt5gra4dxak9";
    private static final String CLIENT_SECRET = "iUOkLW4vzGhSCHofBu3CPfNyu9OXVQ3w";
    private static final String ENTERPRISE_ID = "154175";
    private static final String PUBLIC_KEY_ID = "p7ekvx30";
   // private static final String PRIVATE_KEY_FILE = "-----BEGIN ENCRYPTED PRIVATE KEY-----\\nMIIFDjBABgkqhkiG9w0BBQ0wMzAbBgkqhkiG9w0BBQwwDgQI3gLmGmNG+n0CAggA\\nMBQGCCqGSIb3DQMHBAhyGgOrE/CcFASCBMgyFm/FOV7vJMajuO7y5VCtgHSTJSHS\\nw2I4nkXNYi3K9LSZhyJW4wVCkf/RfywL+fcY/sl38WmY0ep1a+RYxgUlM/QsVXeb\\nY7a/gaqMJ+rBgbBfMf7FkDnqNWquHp8FzGdfTXI992xDn6xaxy3y4MRz6lfyiC80\\nPcuLrbybK4iA5xfItJ/DUXvicCKlDnZ4g4Qoa1wDQuNf0ymCaxDVt7+k8o7kdWXF\\nivM0+p+pQYv2Wuesiuf8eA7tS5QgvXHvKifIlPo3a5LUpQKmHh4yxVyRpC53HfcJ\\ntuYXqEK/02Z93fItnZJ5zubQgUPaBPy7+IBtl/VvwwRPyVKULezCX6X72mIgUaU3\\n2841cXPlshRQhAektel0wyBFrOStEjA2Hn3LCuvYhCUwBLXghVorxAZgzBwa0SRu\\n6S1iVmPcDlfWgg+n85U2WxzxWSJJ0NRnJiVf6ptnxML7bKbelQC8eDNecqb/iYR7\\n7VtV2xUPKQp7r5ay473EHObHCDoYzxzXPhq7rJdlZ1mwxoP82co6nSWOLQ+f5g+h\\noh5A45Qc85TD3+pu12TH5HEJYShD/oPaCULNqTbPTzDaRZmvjBAXhzfvlm7d08pY\\nTdStRu366vdi5qK5Ia4i9luKhwyg4WzI0BYl+DPhDX3bxdJ9+2am9c9o+ByNCf+o\\nC2NJQtZzksdgunb7B3U8Jui1PKMti80xfMuiSXEcyk68EpgQ3G6+9IGb2uBEeKTD\\nncDHiDc+x1E6PbQLrxMyRUAbcoZ5x6LMBCETwOITy5ENKK2eaoJRpNMU1jbtQkcr\\n7Z6AIGiWhxJrq82/u+uYdsLXRwCIa++/ZLrItJPQobaOEoSRiYVt7v85LvFonMZb\\nnSSRConeE0ZD0u6Po3i4/5VLPfiWU8xy1wwJSBYxXDREUWXaL0yofRk8YrI3VY61\\n76qI+fdFlc6k6mLtpKtiq82/MaWnaN1rUMIeRX7/V1kYutfZk1aOUwKWwKwc+wag\\nPyjOmPrTsLPKOwCWRWLexjyCA2wgSIorb7vAOo7TOh2pd4Sky1mGQ8Fg6fJaRdYK\\n25umlglTdvxe3CoLCjcDeua3U0Tom/aKpyfWVW0Np8nO3aBf43R0dupojB6ADpir\\nO7YYGlsBTKblIvH41XZ8eRwlncWOf78u94aXX3m4XTocRr2P4zSwVmzbRziBS+I5\\nO0RbOVaWvQZ0hOkExu3Sij8Zo4/LopFQ5SZyrZipw0QgsTmPFJtX4iVDMVF3sSa4\\n3pl4Xnv5XDD1IjqriMywOcuwOpOoTZChInHeWQosgf60FoQF+xwj5ZHVOZsW8A8/\\nO6LuOE8R4bjO4xLYhS2Z5dkXN7K0Cm3pGIbbrMed2CfQgvSM97+OcJZJt/iih+9e\\nsn+b0Dtp3EtK1nVvp+I3g3am1LXIbmBh8FNMIgm2k+EBRISDVxjYCSRIjt5Ts9uB\\nt/jmXa5WOPrMt2gCf05G/Y/fovXK0Kcj46ukhoJaGZOVk/h8zD73+7IrWVpAL7xH\\n2cY+QnDi1gbcWymg/v87RqP1CcNPzm47lFQbkt2lj7nf28sZIUNWaiS8JGXzIFI3\\nc/DXKtJFGh6GyXSNXvsGc+ZSznFtEx6pjDt8T/0a8lLEr8e7tWitq95g4a5kseun\\n6JM=\\n-----END ENCRYPTED PRIVATE KEY-----";
    private static final String PRIVATE_KEY_FILE = "d:\\Box\\pk.txt";
    private static final String PRIVATE_KEY_PASSWORD = "7b9d8934d5cb24b2300cd065f2f174ee";
    private static final int MAX_CACHE_ENTRIES = 100;
    private static final String FILE = "D:\\Box\\JB_config.json";


    public static void main(String[] args) throws Exception {
        File file = new File(PRIVATE_KEY_FILE);
        byte[] fileData = new byte[(int) file.length()];
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        dis.readFully(fileData);
        dis.close();

        String privateKey = new String(fileData);

        JWTEncryptionPreferences encryptionPref = new JWTEncryptionPreferences();
        encryptionPref.setPublicKeyID(PUBLIC_KEY_ID);
        encryptionPref.setPrivateKey(privateKey);
        encryptionPref.setPrivateKeyPassword(PRIVATE_KEY_PASSWORD);
        encryptionPref.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);
        System.out.println("pk: " + privateKey);
      
        IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(MAX_CACHE_ENTRIES);
       
        BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(
                ENTERPRISE_ID, CLIENT_ID, CLIENT_SECRET, encryptionPref, accessTokenCache);
    
        BoxUser.Info userInfo = BoxUser.getCurrentUser(api).getInfo();
        System.out.format("Welcome, %s!\n\n", userInfo.getName());

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        System.out.println("folder owner: " + rootFolder.getInfo().getOwnedBy().getName());

        String fileId = uploadFile(FILE, api, rootFolder);
        BoxFile boxFile = new BoxFile(api, fileId);
        String ownerLogin = boxFile.getInfo().getOwnedBy().getName();
        System.out.println("file owner name: " + ownerLogin);

    }

    private static String uploadFile(String pathFileName, BoxAPIConnection api, BoxFolder folder) {
        boolean fileExists = false;
        String fileId = null;

        try {
            String fileName = pathFileName.substring(pathFileName.lastIndexOf("/")+1, pathFileName.length());

            for (BoxItem.Info itemInfo : folder) {
                if (itemInfo instanceof BoxFile.Info) {
                    BoxFile.Info fileInfo = (BoxFile.Info) itemInfo;
                    if (fileName.equals(fileInfo.getName())) {
                        fileExists = true;
                        fileId = fileInfo.getID();
                    }
                }
            }

            if (!fileExists) {
                System.out.println("uploading new file: " + fileName);
                FileInputStream stream = new FileInputStream(pathFileName);
                BoxFile.Info boxInfo = folder.uploadFile(stream, pathFileName);
                fileId = boxInfo.getID();
                System.out.println("--- file ID: " + fileId);
                stream.close();
            }
            else {
                System.out.println("uploading new version of file: " + fileName);
                BoxFile file = new BoxFile(api, fileId);
                FileInputStream stream = new FileInputStream(pathFileName);
                file.uploadVersion(stream);
                stream.close();
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return fileId;
    }
}



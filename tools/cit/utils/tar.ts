import { Cmd } from './cmd';

export class TarUtils {

    static tar(tarName: string, sourceDir: string, options: string): Promise<any> {        
	    return Cmd.run(TarUtils.prepareTarCommand(tarName, sourceDir, options));
    }

    static tarNow(tarName: string, sourceDir: string, options: string): any {
        return Cmd.runNow(TarUtils.prepareTarCommand(tarName, sourceDir, options));
    }

    static untar(tarPath: string, destDir: string, options: string): Promise<any> {        
	    return Cmd.run(TarUtils.prepareUnTarCommand(tarPath, destDir));
    }

    static untarNow(tarPath: string, destDir: string, options: string): any {
        return Cmd.runNow(TarUtils.prepareUnTarCommand(tarPath, destDir));
    }

     static zip(zipName: string, sourceDir: string): Promise<any> {        
	    return Cmd.run(TarUtils.prepareZipCommand(zipName, sourceDir));
    }

    static zipNow(zipName: string, sourceDir: string): any {
        return Cmd.runNow(TarUtils.prepareZipCommand(zipName, sourceDir));
    }

    static unzip(zipPath: string, destDir: string): Promise<any> {        
	    return Cmd.run(TarUtils.prepareUnZipCommand(zipPath, destDir));
    }

    static unzipNow(zipPath: string, destDir: string): any {
        return Cmd.runNow(TarUtils.prepareUnZipCommand(zipPath, destDir));
    }

    ///////////// PRIVATE METHODS ///////////////////////////////
    private static prepareTarCommand(tarName: string, sourceDir: string, options: string): string {
        options = options || '';
        let z = (tarName.endsWith("gz")) ? "z" : "";
	    return "COPYFILE_DISABLED=true tar -" + z + "cf " + tarName + " " + options + " " + sourceDir;
    }

    private static prepareUnTarCommand(tarPath: string, destDir: string): string {
        let z = (tarPath.endsWith("gz")) ? "z" : "";
	    return "tar  -" + z + " -o " + destDir + " " + tarPath;
    }

    private static prepareZipCommand(zipName: string, sourceDir: string): string {
        return "zip -q -r " + zipName + " " + sourceDir;
    }

    private static prepareUnZipCommand(zipPath: string, destDir: string): string {
        return "unzip -q -o -d " + destDir + " " + zipPath;
    }
}
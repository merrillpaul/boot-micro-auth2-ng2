/**
 * This iterates all folders inside src/app and searches for *.json , where the wild card
 * represents the locale name.
 * It then generates a module based json which is ultimately mapped and combined to a single json
 * for each locale
 * 
 */
import * as fs from 'fs';
import * as path from 'path';

const i18njson = {};
const ENCODING = 'utf-8';

let jsonFriendlyName = (parent) => {
    let moduleNameParts = parent.toString().split(/[-, \.]+/);
    // camelcases modulename to be json friendly
    let moduleName = moduleNameParts[0];
    if (moduleNameParts.length > 1) {
        let rest = moduleNameParts.slice(1);
        rest.forEach(r => {
            moduleName += r.split('').map((it, n) => {
                if (n === 0) {
                    return it.toUpperCase();
                }
                return it;
            }).join('');
        });
    }
    return moduleName;
}

let findOrCreate = (modulejson: any, parentParts: string[], filename: string) => {
    let childJson;
    if (parentParts.length == 0) {
        return;
    }
    let parentPart = parentParts[0];
    parentPart = jsonFriendlyName(parentPart);
    childJson = modulejson[parentPart];
    if (!childJson) {
        childJson = JSON.parse(fs.readFileSync(filename, ENCODING));
        modulejson[parentPart] = childJson;
        modulejson[parentPart].srcFile = filename.split('src/app')[1];
    }
    findOrCreate(childJson, parentParts.slice(1), filename);
}

let moduleJsons = (dir, modPrefix, parent) => {
    if (!fs.existsSync(dir)) {
        console.log("no dir ", dir);
        return;
    }
    let files = fs.readdirSync(dir);
    // sort alphabetically and low precedence for dir
    files.sort((a, b) => {
        let stat1 = fs.lstatSync(path.join(dir, a));
        let stat2 = fs.lstatSync(path.join(dir, b));
        if (stat1.isDirectory() && !stat2.isDirectory()) {
            return -1;
        } else if (!stat1.isDirectory() && stat2.isDirectory()) {
            return 1;
        }
        if (a === 'i18n' && b !== 'i18n') {
            return -1;
        }
        if (a !== 'i18n' && b === 'i18n') {
            return 1;
        }
        return a > b ? 1 : -1;
    }).forEach(file => {
        let filename = path.join(dir, file);
        let stat = fs.lstatSync(filename);
        if (stat.isDirectory()) {
            let parentStr = [];
            if (parent.trim().length > 0) {
                parentStr.push(parent);
            }
            if (modPrefix.trim().length > 0) {
                parentStr.push(modPrefix);
            }
            moduleJsons(filename, file.toString(), parentStr.join("/"));
        } else if (filename.indexOf(".json") >= 0) {
            console.log('Processing i18njson >>>> ', filename);
            let locale = file.toString().split(".json")[0];
            if (!i18njson[locale]) {
                i18njson[locale] = {};
            }
            let modulejson = i18njson[locale];

            let parentParts = parent.split("/");
            findOrCreate(modulejson, parentParts, filename);
        }
    });
}

export function processI18nFiles(srcDir, destDir) {
    console.log("Process i18n files from ", srcDir);

    moduleJsons(srcDir, "", "");
    if (!fs.existsSync(destDir)) {
        fs.mkdirSync(destDir);
    }
    Object.keys(i18njson).forEach(key => fs.writeFileSync(`${destDir}/${key}.json`,
        JSON.stringify(i18njson[key], null, 5), { encoding: ENCODING }));
};
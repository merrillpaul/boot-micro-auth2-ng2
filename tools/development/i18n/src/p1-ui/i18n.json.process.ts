import { processI18nFiles } from './../utils/i18n.json.processor';
const P1_UI_SRC = '../../../application/projectone-ui/src';
processI18nFiles(`${P1_UI_SRC}/app`, `${P1_UI_SRC}/assets/.i18n`);
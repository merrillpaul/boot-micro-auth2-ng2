import { ProjectOneAppPage } from './app.po';

describe('projectone-ui App', () => {
  let page: ProjectOneAppPage;

  beforeEach(() => {
    page = new ProjectOneAppPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});

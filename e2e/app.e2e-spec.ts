import { MyHomePage } from './app.po';

describe('my-home App', function() {
  let page: MyHomePage;

  beforeEach(() => {
    page = new MyHomePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('myhome works!');
  });
});

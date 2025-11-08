import { test, expect } from "bun:test";
import { parseTopLevelExpressions, applyExamplesTemplate } from "./millModulePlugin";

// Empty/whitespace inputs
test("should return empty array for empty string", () => {
  expect(parseTopLevelExpressions("")).toEqual([]);
});

test("should return empty array for whitespace-only string", () => {
  expect(parseTopLevelExpressions("   \n\t  ")).toEqual([]);
});

// Simple expressions
test("should parse single expression", () => {
  expect(parseTopLevelExpressions("div()")).toEqual(["div()"]);
});

test("should parse multiple simple expressions", () => {
  expect(parseTopLevelExpressions("div() span()")).toEqual(["div()", "span()"]);
});

test("should parse multiple expressions with whitespace", () => {
  expect(parseTopLevelExpressions("div()  \n  span()")).toEqual(["div()", "span()"]);
});

// Nested parentheses
test("should handle nested parentheses", () => {
  expect(parseTopLevelExpressions("div(span())")).toEqual(["div(span())"]);
});

test("should handle deep nesting", () => {
  expect(parseTopLevelExpressions("div(span(a()))")).toEqual(["div(span(a()))"]);
});

test("should split multiple nested expressions", () => {
  expect(parseTopLevelExpressions("div(span()) span(div())")).toEqual([
    "div(span())",
    "span(div())",
  ]);
});

test("should handle multiple nested levels", () => {
  expect(parseTopLevelExpressions("a(b(c(d())))")).toEqual(["a(b(c(d())))"]);
});

// String literals
test("should handle single quotes in strings", () => {
  expect(parseTopLevelExpressions(`div('text')`)).toEqual([`div('text')`]);
});

test("should handle double quotes in strings", () => {
  expect(parseTopLevelExpressions(`div("text")`)).toEqual([`div("text")`]);
});

test("should handle backticks in strings", () => {
  expect(parseTopLevelExpressions("div(`text`)")).toEqual(["div(`text`)"]);
});

test("should handle parentheses inside strings", () => {
  expect(parseTopLevelExpressions(`div("text (with) parens")`)).toEqual([
    `div("text (with) parens")`,
  ]);
});

test("should handle escaped quotes in double-quoted strings", () => {
  expect(parseTopLevelExpressions(`div("text \\"with\\" quotes")`)).toEqual([
    `div("text \\"with\\" quotes")`,
  ]);
});

test("should handle escaped quotes in single-quoted strings", () => {
  expect(parseTopLevelExpressions(`div('text \\'with\\' quotes')`)).toEqual([
    `div('text \\'with\\' quotes')`,
  ]);
});

test("should handle escaped backticks", () => {
  expect(parseTopLevelExpressions("div(`text \\`with\\` backticks`)")).toEqual([
    "div(`text \\`with\\` backticks`)",
  ]);
});

test("should handle multiple escaped backslashes", () => {
  expect(parseTopLevelExpressions(`div("text \\\\escaped")`)).toEqual([
    `div("text \\\\escaped")`,
  ]);
});

test("should handle strings with nested parentheses", () => {
  expect(parseTopLevelExpressions(`div("text (nested) here") span()`)).toEqual([
    `div("text (nested) here")`,
    "span()",
  ]);
});

// Comments
test("should split single-line comments after expression", () => {
  expect(parseTopLevelExpressions("div() // comment")).toEqual(["div()", "// comment"]);
});

test("should handle single-line comments before expression", () => {
  expect(parseTopLevelExpressions("// comment\ndiv()")).toEqual(["// comment\ndiv()"]);
});

test("should split multi-line comments after expression", () => {
  expect(parseTopLevelExpressions("div() /* comment */")).toEqual([
    "div()",
    "/* comment */",
  ]);
});

test("should split multi-line comments spanning lines after expression", () => {
  expect(parseTopLevelExpressions("div() /* comment\nspanning\nlines */")).toEqual([
    "div()",
    "/* comment\nspanning\nlines */",
  ]);
});

test("should split comments with parentheses inside after expression", () => {
  expect(parseTopLevelExpressions("div() // comment (with) parens")).toEqual([
    "div()",
    "// comment (with) parens",
  ]);
});

test("should split multi-line comments with parentheses after expression", () => {
  expect(parseTopLevelExpressions("div() /* comment (with) parens */")).toEqual([
    "div()",
    "/* comment (with) parens */",
  ]);
});

test("should split expressions separated by comments", () => {
  expect(parseTopLevelExpressions("div() // comment\nspan()")).toEqual([
    "div()",
    "// comment\nspan()",
  ]);
});

// Curried calls
test("should not split curried calls", () => {
  expect(parseTopLevelExpressions("div()(props)")).toEqual(["div()(props)"]);
});

test("should handle multiple curried calls", () => {
  expect(parseTopLevelExpressions("div()(props)(config)")).toEqual([
    "div()(props)(config)",
  ]);
});

test("should handle curried calls with nested parentheses", () => {
  expect(parseTopLevelExpressions("div()(props(a()))")).toEqual(["div()(props(a()))"]);
});

test("should split after curried call", () => {
  expect(parseTopLevelExpressions("div()(props) span()")).toEqual([
    "div()(props)",
    "span()",
  ]);
});

test("should handle whitespace between curried calls", () => {
  expect(parseTopLevelExpressions("div()  (props)")).toEqual(["div()  (props)"]);
});

// Complex combinations
test("should split expression with string and comment after", () => {
  expect(parseTopLevelExpressions(`div("text") // comment`)).toEqual([
    `div("text")`,
    "// comment",
  ]);
});

test("should handle nested expression with string", () => {
  expect(parseTopLevelExpressions(`div(span("text"))`)).toEqual([
    `div(span("text"))`,
  ]);
});

test("should handle multiple expressions with strings and comments", () => {
  expect(parseTopLevelExpressions(`div("text") // comment\nspan('other')`)).toEqual([
    `div("text")`,
    `// comment\nspan('other')`,
  ]);
});

test("should handle real-world Scala code example", () => {
  const code = `div(
  className := "container",
  span("Hello")
)
span("World")`;
  expect(parseTopLevelExpressions(code)).toEqual([
    `div(
  className := "container",
  span("Hello")
)`,
    `span("World")`,
  ]);
});

test("should handle complex nested structure with strings", () => {
  const code = `div(
  span("text"),
  a(href := "#", "link")
)
button("click")`;
  expect(parseTopLevelExpressions(code)).toEqual([
    `div(
  span("text"),
  a(href := "#", "link")
)`,
    `button("click")`,
  ]);
});

test("should handle curried call with strings and comments", () => {
  const code = `div()("text") // comment
span()`;
  expect(parseTopLevelExpressions(code)).toEqual([
    `div()("text")`,
    `// comment\nspan()`,
  ]);
});

test("should handle expression with multi-line comment and string", () => {
  const code = `div("text") /* multi-line
comment */ span()`;
  expect(parseTopLevelExpressions(code)).toEqual([
    `div("text")`,
    `/* multi-line\ncomment */ span()`,
  ]);
});

test("should handle empty parentheses", () => {
  expect(parseTopLevelExpressions("div()")).toEqual(["div()"]);
});

test("should handle expression with only whitespace inside parentheses", () => {
  expect(parseTopLevelExpressions("div(   )")).toEqual(["div(   )"]);
});

test("should handle expression with newlines inside parentheses", () => {
  const code = `div(
)`;
  expect(parseTopLevelExpressions(code)).toEqual([`div(
)`]);
});

test("should handle multiple Button expressions with curried calls from button.mdx", () => {
  const code = `Button(_.appearance.accent, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)
Button(_.appearance.filled, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)
Button(_.appearance.outlined, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)
Button(_.appearance.plain, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)`;
  expect(parseTopLevelExpressions(code)).toEqual([
    `Button(_.appearance.accent, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)`,
    `Button(_.appearance.filled, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)`,
    `Button(_.appearance.outlined, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)`,
    `Button(_.appearance.plain, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)`,
  ]);
});

// Tests for applyExamplesTemplate with grouping
test("applyExamplesTemplate should wrap single group in div with Examples", () => {
  const code = `Button(_.variant.brand)("Brand")
Button(_.variant.danger)("Danger")
Button(_.variant.neutral)("Neutral")`;
  const result = applyExamplesTemplate({
    prefix: "test/button",
    hash: "test123",
    userCode: code,
  });
  
  expect(result).toContain("div(");
  expect(result).toContain("Examples(");
  expect(result).toContain('Button(_.variant.brand)("Brand")');
  expect(result).toContain('Button(_.variant.danger)("Danger")');
  expect(result).toContain('Button(_.variant.neutral)("Neutral")');
  // Should have only one Examples call
  const examplesMatches = result.match(/Examples\(/g);
  expect(examplesMatches?.length).toBe(1);
});

test("applyExamplesTemplate should group expressions by blank lines", () => {
  const code = `Button(_.appearance.accent, _.variant.neutral)("Accent")
Button(_.appearance.filled, _.variant.neutral)("Filled")

Button(_.appearance.accent, _.variant.brand)("Accent")
Button(_.appearance.filled, _.variant.brand)("Filled")`;
  const result = applyExamplesTemplate({
    prefix: "test/button",
    hash: "test123",
    userCode: code,
  });
  
  expect(result).toContain("div(");
  // Should have two Examples calls
  const examplesMatches = result.match(/Examples\(/g);
  expect(examplesMatches?.length).toBe(2);
  // First group should contain neutral variant buttons
  expect(result).toContain('Button(_.appearance.accent, _.variant.neutral)("Accent")');
  expect(result).toContain('Button(_.appearance.filled, _.variant.neutral)("Filled")');
  // Second group should contain brand variant buttons
  expect(result).toContain('Button(_.appearance.accent, _.variant.brand)("Accent")');
  expect(result).toContain('Button(_.appearance.filled, _.variant.brand)("Filled")');
});

test("applyExamplesTemplate should handle Appearance section with 5 groups", () => {
  const code = `Button(_.appearance.accent, _.variant.neutral)("Accent")
Button(_.appearance.filledOutlined, _.variant.neutral)("Filled + Outlined")
Button(_.appearance.filled, _.variant.neutral)("Filled")
Button(_.appearance.outlined, _.variant.neutral)("Outlined")
Button(_.appearance := "plain", _.variant := "neutral")("Plain")

Button(_.appearance.accent, _.variant.brand)("Accent")
Button(_.appearance.filledOutlined, _.variant.brand)("Filled + Outlined")
Button(_.appearance.filled, _.variant.brand)("Filled")
Button(_.appearance.outlined, _.variant.brand)("Outlined")
Button(_.appearance.plain, _.variant.brand)("Plain")

Button(_.appearance.accent, _.variant.success)("Accent")
Button(_.appearance.filledOutlined, _.variant.success)("Filled + Outlined")
Button(_.appearance.filled, _.variant.success)("Filled")
Button(_.appearance.outlined, _.variant.success)("Outlined")
Button(_.appearance.plain, _.variant.success)("Plain")

Button(_.appearance.accent, _.variant.warning)("Accent")
Button(_.appearance.filledOutlined, _.variant.warning)("Filled + Outlined")
Button(_.appearance.filled, _.variant.warning)("Filled")
Button(_.appearance.outlined, _.variant.warning)("Outlined")
Button(_.appearance.plain, _.variant.warning)("Plain")

Button(_.appearance.accent, _.variant.danger)("Accent")
Button(_.appearance.filledOutlined, _.variant.danger)("Filled + Outlined")
Button(_.appearance.filled, _.variant.danger)("Filled")
Button(_.appearance.outlined, _.variant.danger)("Outlined")
Button(_.appearance.plain, _.variant.danger)("Plain")`;
  const result = applyExamplesTemplate({
    prefix: "webawesome/button",
    hash: "test123",
    userCode: code,
  });
  
  expect(result).toContain("div(");
  // Should have 5 Examples calls (one per group)
  const examplesMatches = result.match(/Examples\(/g);
  expect(examplesMatches?.length).toBe(5);
  // Verify each group contains the correct buttons
  expect(result).toContain('_.variant.neutral');
  expect(result).toContain('_.variant.brand');
  expect(result).toContain('_.variant.success');
  expect(result).toContain('_.variant.warning');
  expect(result).toContain('_.variant.danger');
});

test("applyExamplesTemplate should not add trailing comma after last Examples call", () => {
  const code = `Button(_.variant.brand)("Brand")

Button(_.variant.danger)("Danger")`;
  const result = applyExamplesTemplate({
    prefix: "test/button",
    hash: "test123",
    userCode: code,
  });
  
  // Extract the div content between div( and )
  const divStart = result.indexOf("div(");
  const divEnd = result.lastIndexOf(")");
  expect(divStart).not.toBe(-1);
  expect(divEnd).not.toBe(-1);
  
  // Find all Examples( occurrences
  const examplesMatches = [...result.matchAll(/Examples\(/g)];
  expect(examplesMatches.length).toBe(2);
  
  // Check that first Examples call ends with comma
  const firstExamplesEnd = result.indexOf("),", examplesMatches[0].index!);
  expect(firstExamplesEnd).not.toBe(-1);
  
  // Check that second Examples call does NOT have trailing comma before closing div
  const secondExamplesStart = examplesMatches[1].index!;
  const secondExamplesEnd = result.indexOf(")", secondExamplesStart);
  const afterSecondExamples = result.substring(secondExamplesEnd, divEnd);
  // Should not have comma immediately after second Examples closing paren
  expect(afterSecondExamples.trim()).not.toMatch(/^,\s*$/);
});

test("applyExamplesTemplate should handle single expression per group", () => {
  const code = `Button(_.variant.brand)("Brand")

Button(_.variant.danger)("Danger")

Button(_.variant.neutral)("Neutral")`;
  const result = applyExamplesTemplate({
    prefix: "test/button",
    hash: "test123",
    userCode: code,
  });
  
  // Should have 3 Examples calls (one per group)
  const examplesMatches = result.match(/Examples\(/g);
  expect(examplesMatches?.length).toBe(3);
  expect(result).toContain("div(");
});


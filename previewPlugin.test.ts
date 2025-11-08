import { describe, test, expect } from "bun:test";
import { parseTopLevelExpressions, applyExamplesTemplate } from "./previewPlugin";

interface TemplateContext {
  number: number;
  userCode: string;
  modulePathParts: string[];
}

describe("parseTopLevelExpressions", () => {
  test("should parse simple Button calls", () => {
    const input = `Button(_.variant.brand)("Brand")
Button(_.variant.danger)("Danger")`;
    
    // Expected: Two separate expressions
    const result = parseTopLevelExpressions(input);
    expect(result).toHaveLength(2);
    expect(result[0]).toContain("Button(_.variant.brand)");
    expect(result[1]).toContain("Button(_.variant.danger)");
  });

  test("should preserve nested Icon structure inside Button", () => {
    const input = `Button(_.appearance.accent, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)
Button(_.appearance.filled, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)`;
    
    const result = parseTopLevelExpressions(input);
    expect(result).toHaveLength(2);
    
    // First expression should contain the Button with nested Icon
    expect(result[0]).toContain("Button(_.appearance.accent");
    expect(result[0]).toContain("Icon(_.name := \"house\"");
    expect(result[0]).toContain("\n  Icon"); // Preserve newline before Icon
    
    // Second expression should also contain Button with nested Icon
    expect(result[1]).toContain("Button(_.appearance.filled");
    expect(result[1]).toContain("Icon(_.name := \"house\"");
  });

  test("should handle string literals with parentheses", () => {
    const input = `Button(_.label := "test (with) parens")("Text")
Button(_.label := "another")("More")`;
    
    const result = parseTopLevelExpressions(input);
    expect(result).toHaveLength(2);
    expect(result[0]).toContain("test (with) parens");
  });

  test("should handle escaped quotes in strings", () => {
    const input = `Button(_.label := "test \\"quote\\"")("Text")
Button(_.label := "normal")("More")`;
    
    const result = parseTopLevelExpressions(input);
    expect(result).toHaveLength(2);
    expect(result[0]).toContain('test \\"quote\\"');
  });

  test("should handle single-line comments", () => {
    const input = `Button(_.variant.brand)("Brand") // comment
Button(_.variant.danger)("Danger")`;
    
    const result = parseTopLevelExpressions(input);
    expect(result).toHaveLength(2);
    // Comments on the same line should be preserved
    expect(result[0]).toContain("Button(_.variant.brand)");
    expect(result[1]).toContain("Button(_.variant.danger)");
  });

  test("should handle multi-line comments", () => {
    const input = `Button(_.variant.brand)("Brand") /* comment
with multiple lines */
Button(_.variant.danger)("Danger")`;
    
    const result = parseTopLevelExpressions(input);
    expect(result).toHaveLength(2);
    // Multi-line comments should be preserved within expressions
    expect(result[0]).toContain("Button(_.variant.brand)");
    expect(result[1]).toContain("Button(_.variant.danger)");
  });

  test("should handle empty input", () => {
    const result = parseTopLevelExpressions("");
    expect(result).toHaveLength(0);
  });

  test("should handle whitespace-only input", () => {
    const result = parseTopLevelExpressions("   \n  \n  ");
    expect(result).toHaveLength(0);
  });

  test("should handle multiple nested levels", () => {
    const input = `Button(_.variant.brand)(
  Div(
    Icon(_.name := "test")()
  )()
)
Button(_.variant.danger)("Danger")`;
    
    const result = parseTopLevelExpressions(input);
    expect(result).toHaveLength(2);
    expect(result[0]).toContain("Div(");
    expect(result[0]).toContain("Icon(");
  });

  test("should preserve formatting within expressions", () => {
    const input = `Button(_.appearance.accent, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)`;
    
    const result = parseTopLevelExpressions(input);
    expect(result).toHaveLength(1);
    // Should preserve the newline and indentation
    expect(result[0]).toMatch(/\n\s+Icon/);
  });

  test("should correctly parse multiple Button expressions with nested Icons", () => {
    const input = `Button(_.appearance.accent, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)
Button(_.appearance.filled, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)
Button(_.appearance.outlined, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)`;
    
    const result = parseTopLevelExpressions(input);
    expect(result).toHaveLength(3);
    
    // Each expression should contain Button and nested Icon
    expect(result[0]).toContain("Button(_.appearance.accent");
    expect(result[0]).toContain("Icon(_.name := \"house\"");
    expect(result[0]).toContain("\n  Icon"); // Preserve newline before Icon
    
    expect(result[1]).toContain("Button(_.appearance.filled");
    expect(result[1]).toContain("Icon(_.name := \"house\"");
    expect(result[1]).toContain("\n  Icon");
    
    expect(result[2]).toContain("Button(_.appearance.outlined");
    expect(result[2]).toContain("Icon(_.name := \"house\"");
    expect(result[2]).toContain("\n  Icon");
    
    // Verify the exact expected output format when joined with commas
    // Note: Last expression should not have a comma
    const joined = result.map((expr, index) => {
      if (index === result.length - 1) {
        return expr; // Last expression: no comma
      }
      return expr.endsWith(",") ? expr : `${expr},`;
    }).join("\n");
    const expectedOutput = `Button(_.appearance.accent, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
),
Button(_.appearance.filled, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
),
Button(_.appearance.outlined, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)`;
    
    expect(joined).toBe(expectedOutput);
  });
});

describe("applyExamplesTemplate", () => {
  test("should wrap expressions in Examples call", () => {
    const ctx: TemplateContext = {
      number: 1,
      userCode: `Button(_.variant.brand)("Brand")
Button(_.variant.danger)("Danger")`,
      modulePathParts: ["webawesome", "button"],
    };
    
    const result = applyExamplesTemplate(ctx);
    
    expect(result).toContain("package examples.webawesome.button.example1");
    expect(result).toContain("Examples(");
    expect(result).toContain("Button(_.variant.brand)");
    expect(result).toContain("Button(_.variant.danger)");
    expect(result).toContain(","); // Should have commas between expressions
  });

  test("should preserve nested Icon structure", () => {
    const ctx: TemplateContext = {
      number: 2,
      userCode: `Button(_.appearance.accent, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)
Button(_.appearance.filled, _.variant.neutral)(
  Icon(_.name := "house", _.label := "Home")()
)`,
      modulePathParts: ["webawesome", "button"],
    };
    
    const result = applyExamplesTemplate(ctx);
    
    // Should have commas between Button expressions
    expect(result).toContain("Button(_.appearance.accent, _.variant.neutral)(");
    expect(result).toContain("Button(_.appearance.filled, _.variant.neutral)(");
    expect(result).toContain(","); // Should have commas between expressions
    
    // Should preserve Icon structure (no comma after Icon on the same line)
    expect(result).toContain("Icon(_.name := \"house\"");
    expect(result).toContain("\n          Icon"); // Should preserve newline and indentation before Icon
  });

  test("should generate correct package name", () => {
    const ctx: TemplateContext = {
      number: 1,
      userCode: `Button(_.variant.brand)("Brand")`,
      modulePathParts: ["laminar", "components"],
    };
    
    const result = applyExamplesTemplate(ctx);
    expect(result).toContain("package examples.laminar.components.example1");
  });

  test("should handle empty module path parts", () => {
    const ctx: TemplateContext = {
      number: 1,
      userCode: `Button(_.variant.brand)("Brand")`,
      modulePathParts: [],
    };
    
    const result = applyExamplesTemplate(ctx);
    expect(result).toContain("package examples.example1");
  });
});



package au.edu.unimelb.cis.dragons.html;

import java.util.HashSet;
import java.util.Set;

import au.edu.unimelb.cis.dragons.core.CustomGraphics;
import playn.core.CanvasImage;
import playn.core.Font;
import playn.core.Font.Style;
import playn.core.Gradient;
import playn.core.GroupLayer;
import playn.core.GroupLayer.Clipped;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ImmediateLayer;
import playn.core.ImmediateLayer.Renderer;
import playn.core.SurfaceImage;
import playn.core.SurfaceLayer;
import playn.core.TextFormat;
import playn.core.TextLayout;
import playn.core.gl.GL20;
import playn.core.gl.GLContext;
import playn.html.HtmlGraphics;

/**
 * Implementation of CustomGraphics for the HTML platform.
 * 
 * @author Aidan Nagorcka-Smith (aidanns@gmail.com)
 */
public class HtmlCustomGraphics implements CustomGraphics {
	
	// The underlying graphics that we forward all non-handler related messages
	// to.
	private HtmlGraphics _delegateGraphics;
	
	// The resize handlers that we've registered.
	private Set<ResizeHandler> _resizeHandlers = new HashSet<ResizeHandler>();

	public HtmlCustomGraphics(HtmlGraphics g) {
		_delegateGraphics = g;
	}

	@Override
	public void removeResizeHandler(ResizeHandler handler) {
		_resizeHandlers.remove(handler);
	}
	
	@Override
	public void setSize(int width, int height) {
		_delegateGraphics.setSize(width, height);
		for (ResizeHandler h : _resizeHandlers) {
			h.handleResize(width, height);
		}
	}

	@Override
	public void addResizeHandler(ResizeHandler handler) {
		_resizeHandlers.add(handler);
	}

	@Override
	public int width() {
		return _delegateGraphics.width();
	}

	@Override
	public int height() {
		return _delegateGraphics.height();
	}

	@Override
	public int screenHeight() {
		return _delegateGraphics.screenHeight();
	}

	@Override
	public int screenWidth() {
		return _delegateGraphics.screenWidth();
	}

	@Override
	public float scaleFactor() {
		return _delegateGraphics.scaleFactor();
	}

	@Override
	public GroupLayer rootLayer() {
		return _delegateGraphics.rootLayer();
	}

	@Override
	public GLContext ctx() {
		return _delegateGraphics.ctx();
	}

	@Override
	public GL20 gl20() {
		return _delegateGraphics.gl20();
	}

	@Override
	public GroupLayer createGroupLayer() {
		return _delegateGraphics.createGroupLayer();
	}

	@Override
	public Clipped createGroupLayer(float width, float height) {
		return _delegateGraphics.createGroupLayer(width, height);
	}

	@Override
	public playn.core.ImmediateLayer.Clipped createImmediateLayer(int width,
			int height, Renderer renderer) {
		return _delegateGraphics.createImmediateLayer(width, height, renderer);
	}

	@Override
	public ImmediateLayer createImmediateLayer(Renderer renderer) {
		return _delegateGraphics.createImmediateLayer(renderer);
	}

	@Override
	public ImageLayer createImageLayer() {
		return _delegateGraphics.createImageLayer();
	}

	@Override
	public ImageLayer createImageLayer(Image image) {
		return _delegateGraphics.createImageLayer(image);
	}

	@Override
	@Deprecated
	public SurfaceLayer createSurfaceLayer(float width, float height) {
		return _delegateGraphics.createSurfaceLayer(width, height);
	}

	@Override
	public CanvasImage createImage(float width, float height) {
		return _delegateGraphics.createImage(width, height);
	}

	@Override
	public SurfaceImage createSurface(float width, float height) {
		return _delegateGraphics.createSurface(width, height);
	}

	@Override
	public Gradient createLinearGradient(float x0, float y0, float x1,
			float y1, int[] colors, float[] positions) {
		return _delegateGraphics.createLinearGradient(x0, y0, x1, y1, colors, positions);
	}

	@Override
	public Gradient createRadialGradient(float x, float y, float r,
			int[] colors, float[] positions) {
		return _delegateGraphics.createRadialGradient(x, y, r, colors, positions);
	}

	@Override
	public Font createFont(String name, Style style, float size) {
		return _delegateGraphics.createFont(name, style, size);
	}

	@Override
	public TextLayout layoutText(String text, TextFormat format) {
		return _delegateGraphics.layoutText(text, format);
	}

}
